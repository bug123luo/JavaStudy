package com.ubo.terminal.handler;

import com.ubo.common.terminal.*;
import dudu.service.core.terminal.TerMinalBasicMessage;
import dudu.service.pojo.ClientHeartBeatBody;
import dudu.service.pojo.ClientHeartBeatMessage;
import dudu.service.pojo.UboSimpleMessage;
import io.netty.channel.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;

import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.core.terminal.HeartbeatMessage;
import dudu.service.core.utils.Utils;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.ConcurrentHashMap;


public class HeartbeatMessageHandler extends SimpleMessageHandler implements ProtocolHandler {
	
	private static final Logger LOG=LoggerFactory.getLogger(HeartbeatMessageHandler.class);
	
	public HeartbeatMessageHandler() {
		super(LOG);
	}
	
	public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
		throws Exception {
		
		if (!(message instanceof UboHeartbeatMessage)) {
			LOG.error(String.format(
				"channel [%s]: Invalid Status Message!",
				ctx.channel().id().asShortText()));
			return;
		}

		UboHeartbeatMessage heartbeatMsg = (UboHeartbeatMessage)message;

		ConcurrentHashMap<String, Channel> channelMap =
				AccessServer.getInstance().getChannelMap();

		LOG.info("当前连接有：{}",channelMap);

		String sessionToken = heartbeatMsg.getAuthCode();
		if (channelMap.containsKey(sessionToken)) {
			ChannelPipeline pipeline = ctx.pipeline();
			SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
			session.setToken(sessionToken);

			channelMap.put(sessionToken, ctx.channel());
		}else{
			LOG.info("本次请求未登录token{},请先登录", heartbeatMsg.getAuthCode());
			return;
		}
		//1 reply terminal
		UboSimpleMessage replyMsg = new UboSimpleMessage();
		replyMsg.setServiceType(heartbeatMsg.getTid());
		replyMsg.setFormatVersion(heartbeatMsg.getDeviceVersion());
		replyMsg.setDeviceType(Integer.parseInt(heartbeatMsg.getDeviceType()));
		replyMsg.setSerialNumber(heartbeatMsg.getSerial());
		replyMsg.setMessageType("14");
		replyMsg.setMessageBody("["+"0"+";"+heartbeatMsg.getAuthCode()+"]");
		replyMsg.setSendTime(heartbeatMsg.getSerial().substring(0,14));
		ChannelFuture future = ctx.channel().write(replyMsg);
		
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				if (!future.isSuccess()) {
					LOG.error("Fail to replay heartbeat message!");
					LOG.error(Utils.getThrowableInfo(future.cause()));
				}
			}
		});

		ClientHeartBeatMessage authCodeMessage = new ClientHeartBeatMessage();

		ClientHeartBeatBody clientHeartBeatBody = new ClientHeartBeatBody();
		BeanUtils.copyProperties(heartbeatMsg,clientHeartBeatBody);

		authCodeMessage.setServiceType(heartbeatMsg.getTid());
		authCodeMessage.setFormatVersion(heartbeatMsg.getDeviceVersion());
		authCodeMessage.setDeviceType(Integer.parseInt(heartbeatMsg.getDeviceType()));
		authCodeMessage.setSerialNumber(heartbeatMsg.getSerial());
		authCodeMessage.setMessageType(heartbeatMsg.getCmd());

		authCodeMessage.setSendTime(heartbeatMsg.getSerial().substring(0,14));
		authCodeMessage.setSessionToken(sessionToken);
		authCodeMessage.setMessageBody(clientHeartBeatBody);

		String heatMessage = JSONObject.fromObject(authCodeMessage).toString();
		sendMessage("object/" + heartbeatMsg.getType(), heatMessage);

		LOG.info("心跳消息发送成功{}", heatMessage);
	}
}
