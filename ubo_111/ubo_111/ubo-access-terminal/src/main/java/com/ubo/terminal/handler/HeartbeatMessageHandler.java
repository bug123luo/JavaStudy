package com.ubo.terminal.handler;

import com.ubo.common.terminal.*;
import dudu.service.core.terminal.TerMinalBasicMessage;
import dudu.service.pojo.ClientHeartBeatBody;
import dudu.service.pojo.ClientHeartBeatMessage;
import dudu.service.pojo.UboSimpleMessage;
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
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.BeanUtils;


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
		SessionChannelHandler.Session session = ctx.channel().pipeline().get(SessionChannelHandler.class).getSession();

		//1 reply terminal
		UboSimpleMessage replyMsg = new UboSimpleMessage();
		replyMsg.setServiceType(heartbeatMsg.getTid());
		replyMsg.setFormatVersion(heartbeatMsg.getDeviceVersion());
		replyMsg.setDeviceType(Integer.parseInt(heartbeatMsg.getDeviceType()));
		replyMsg.setSerialNumber(heartbeatMsg.getSerial());
		replyMsg.setMessageType("14");
		replyMsg.setMessageBody("["+"0"+";"+session.getToken()+"]");
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
		
		String sessionToken = session.getToken();
		//2 send heart beat to login server



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
