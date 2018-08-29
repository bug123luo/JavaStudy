package com.ubo.terminal;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.common.terminal.UboTerminalMessage;
import com.ubo.terminal.handler.CloseMessageHandler;

import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolDecoder;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.terminal.CloseMessage;
import dudu.service.core.utils.Utils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.ReferenceCountUtil;

public class SessionChannelHandler extends ChannelHandlerAdapter {

	private static final Logger LOG=LoggerFactory.getLogger(SessionChannelHandler.class);
	private ProtocolDecoder decoder;
	private HashMap<String, ProtocolHandler> handlerMap;
	private Session session;

	public SessionChannelHandler() {

	}

	public SessionChannelHandler(ProtocolDecoder decoder,
								 HashMap<String, ProtocolHandler> handlerMap) {
		this.decoder = decoder;
		this.handlerMap = handlerMap;
	}

	public Session getSession() {
		return session;
	}

	public static class Session {

		private long msgId;
		private String eqId;
		private String token;
		private String protocolVer;
		private byte exitCode;
		private String lastRecSerial;

		public Session() {
			msgId = 0;
			eqId = null;
			exitCode = 0;
			lastRecSerial = null;
		}

		public Long generateMsgId() {

			if (msgId == 9999) {
				msgId = 0;
			}

			return ++msgId;
		}

		public String getEqId() {
			return eqId;
		}

		public void setEqId(String eqId) {
			this.eqId = eqId;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getProtocolVer() {
			return protocolVer;
		}

		public void setProtocolVer(String protocolVer) {
			this.protocolVer = protocolVer;
		}

		public byte getExitCode() {
			return exitCode;
		}

		public void setExitCode(byte exitCode) {
			this.exitCode = exitCode;
		}

		public String getLastRecSerial() {
			return lastRecSerial;
		}

		public void setLastRecSerial(String lastRecSerial) {
			this.lastRecSerial = lastRecSerial;
		}

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		ChannelGroup channelGroup = AccessServer.getInstance().getChannelGroup();
		channelGroup.add(ctx.channel());
		LOG.info(String.format(
				"channel[%s] active, channel group size: %d.",
				ctx.channel().id().asShortText(),
				channelGroup.size()));

		String token = Utils.channelSessionDigest();
		session = new Session();
		session.setToken(token);
		LOG.debug("Channel session token: {}.", token);

		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		ChannelGroup channelGroup = AccessServer.getInstance().getChannelGroup();
		ConcurrentHashMap<String, Channel> channelMap =
				AccessServer.getInstance().getChannelMap();

		SessionChannelHandler sessionHandler = (SessionChannelHandler)ctx.channel().pipeline().get(SessionChannelHandler.class.getName());
		String sessionToken = sessionHandler.getSession().getToken();

		//remove channel from channel group
		LOG.info(String.format(
				"[%s]channel[%s] inactive preparing, channel group size: %d.",
				sessionToken,
				ctx.channel().id().asShortText(),
				channelGroup.size()));

		Channel channel = ctx.channel();
		String token = session.getToken();

		if (channelGroup.contains(channel)) {
			channelGroup.remove(channel);

			LOG.debug(String.format(
					"[%]channel[%s] is removed from channel group, imei: %s.",
					sessionToken,
					channel.id().asShortText(),
					token));
		}

		//remove channel from channel map
		LOG.info("[{}]channel map size: {}.", sessionToken, channelMap.size());
		if (LOG.isDebugEnabled()) {
			Utils.dumpMap(channelMap);
		}

		/*if (channelMap.containsValue(channel)) {
			channelMap.remove(token);

			LOG.debug(String.format(
					"[%s]channel[%s] is removed from channel map, imei: %s.",
					sessionToken,
					channel.id().asShortText(),
					token));
		}*/

		LOG.info(String.format(
				"[%s]channel[%s] inactive, imei: \"%s\", channel group size: %d, channel map size: %d.",
				sessionToken,
				channel.id().asShortText(),
				token,
				channelGroup.size(),
				channelMap.size()));

		if (LOG.isDebugEnabled()) {
			Utils.dumpMap(channelMap);
		}

		//notify logic server, the terminal is off line.
		/*if (token != null) {
			CloseMessage closeMsg = new CloseMessage();
			closeMsg.setType("CLOSE");
			closeMsg.setEqId(token);
			closeMsg.setSessionToken(session.getToken());
			closeMsg.setExitCode(session.getExitCode());

			CloseMessageHandler handler = (CloseMessageHandler)handlerMap.get("CLOSE");
			handler.handleMessage(ctx, closeMsg);
		}*/

		ctx.fireChannelInactive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		LOG.debug(".channelRead()");

		if (msg == null) {
			return;
		}

		try {

			if (msg instanceof UboTerminalMessage) {
				UboTerminalMessage tMsg = (UboTerminalMessage)msg;

				MessageBean msgBean = null;
				if (decoder != null) {
					msgBean = (MessageBean)this.decoder.decode(ctx, ctx.channel(), tMsg);
				} else {
					throw new Exception("No messaage decoder.");
				}

				if (msgBean != null) {
					LOG.debug("message type: {}.", msgBean.getType());

					if (handlerMap.containsKey(msgBean.getType())) {
						ProtocolHandler handler =
								(ProtocolHandler)handlerMap.get(msgBean.getType());
						handler.handleMessage(ctx, msgBean);

					} else {
						ProtocolHandler handler =
								(ProtocolHandler)handlerMap.get("default");
						handler.handleMessage(ctx, msgBean);
					}
				} else {
					LOG.error("Invalid decoded message!");
				}
			}

		} finally {
			ReferenceCountUtil.release(msg);
		}

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		LOG.debug(".channelReadComplete()");
		ctx.fireChannelReadComplete();
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		LOG.debug(".write()");
		ctx.write(msg, promise);
		flush(ctx);
	}

	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		LOG.debug(".flush()");
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		SessionChannelHandler sessionHandler = (SessionChannelHandler)ctx.channel().pipeline().get(SessionChannelHandler.class.getName());
		String sessionToken = sessionHandler.getSession().getToken();

		LOG.error(String.format(
				"[%s][%s] ChannelHandler Exception: %s",
				sessionToken,
				ctx.channel().id().asShortText(),
				Utils.getThrowableInfo(cause)));

		session.setExitCode((byte)5);
		ctx.channel().close();
	}

}


