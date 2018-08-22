package com.ubo.terminal.handler;

import com.ubo.common.terminal.SOSRequestMessage;
import com.ubo.terminal.SessionChannelHandler;
import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.pojo.ClientOffLocationWarningBody;
import dudu.service.pojo.ClientOffLocationWarningMessage;
import io.netty.channel.ChannelHandlerContext;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;


public class SOSMessageHandler extends SimpleMessageHandler implements ProtocolHandler {

	private static final Logger LOG=LoggerFactory.getLogger(MarryMessageHandler.class);

	public SOSMessageHandler() {
		super(LOG);
	}
	
	public void handleMessage(ChannelHandlerContext ctx, MessageBean message) throws Exception {
		
		if (!(message instanceof SOSRequestMessage)) {
			LOG.error(String.format(
				"channel [%s]: Invalid Status Message!",
				ctx.channel().id().asShortText()));
			return;
		}

        SOSRequestMessage sosRequestMessage = (SOSRequestMessage)message;
		SessionChannelHandler.Session session = ctx.channel().pipeline().get(SessionChannelHandler.class).getSession();

		/*//1 reply terminal
		UboSimpleMessage replyMsg = new UboSimpleMessage();
		replyMsg.setServiceType(sosRequestMessage.getTid());
		replyMsg.setFormatVersion(sosRequestMessage.getDeviceVersion());
		replyMsg.setDeviceType(Integer.parseInt(sosRequestMessage.getDeviceType()));
		replyMsg.setSerialNumber(sosRequestMessage.getSerial());
		replyMsg.setMessageType("16");
		replyMsg.setMessageBody("0@"+session.getToken());
		replyMsg.setSendTime(sosRequestMessage.getSerial().substring(0,14));
		ChannelFuture future = ctx.channel().write(replyMsg);
		
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				if (!future.isSuccess()) {
					LOG.error("Fail to replay heartbeat message!");
					LOG.error(Utils.getThrowableInfo(future.cause()));
				}
			}
		});*/

		ClientOffLocationWarningMessage clientOffLocationWarningMessage = new ClientOffLocationWarningMessage();

		//发送消息到业务模块
		ClientOffLocationWarningBody clientOffLocationWarningBody = new ClientOffLocationWarningBody();
		BeanUtils.copyProperties(sosRequestMessage,clientOffLocationWarningBody);

		clientOffLocationWarningMessage.setServiceType(sosRequestMessage.getTid());
		clientOffLocationWarningMessage.setFormatVersion(sosRequestMessage.getDeviceVersion());
		clientOffLocationWarningMessage.setDeviceType(Integer.parseInt(sosRequestMessage.getDeviceType()));
		clientOffLocationWarningMessage.setSerialNumber(sosRequestMessage.getSerial());
		clientOffLocationWarningMessage.setMessageType(sosRequestMessage.getCmd());
		clientOffLocationWarningMessage.setSendTime(sosRequestMessage.getSerial().substring(0, 14));
		clientOffLocationWarningMessage.setSessionToken(session.getToken());
		clientOffLocationWarningMessage.setMessageBody(sosRequestMessage);
		clientOffLocationWarningMessage.setMessageBody(clientOffLocationWarningBody);
		JSONObject jsonObject = JSONObject.fromObject(clientOffLocationWarningMessage);
		String loginMessage = jsonObject.toString();
		sendMessage("object/" + sosRequestMessage.getType(), loginMessage);
		LOG.info("报警消息发送成功{}", loginMessage);
	}
}
