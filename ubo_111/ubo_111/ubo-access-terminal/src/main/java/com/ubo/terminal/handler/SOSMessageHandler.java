package com.ubo.terminal.handler;

import com.ubo.common.terminal.SOSRequestMessage;
import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;
import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.pojo.ClientOffLocationWarningBody;
import dudu.service.pojo.ClientOffLocationWarningMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.ConcurrentHashMap;


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
		ConcurrentHashMap<String, Channel> channelMap =
				AccessServer.getInstance().getChannelMap();

		String sessionToken = sosRequestMessage.getAuthCode();
		if (channelMap.containsKey(sessionToken)) {
			ChannelPipeline pipeline = ctx.pipeline();
			SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
			session.setToken(sessionToken);

			channelMap.put(sessionToken, ctx.channel());
		}else{
			LOG.info("本次请求未登录token{},请先登录",sessionToken);
			return;
		}

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
		clientOffLocationWarningMessage.setSessionToken(sessionToken);
		clientOffLocationWarningMessage.setMessageBody(sosRequestMessage);
		clientOffLocationWarningMessage.setMessageBody(clientOffLocationWarningBody);
		JSONObject jsonObject = JSONObject.fromObject(clientOffLocationWarningMessage);
		String loginMessage = jsonObject.toString();
		sendMessage("object/" + sosRequestMessage.getType(), loginMessage);
		LOG.info("报警消息发送成功{}", loginMessage);
	}
}
