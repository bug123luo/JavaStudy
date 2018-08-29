package com.ubo.terminal.handler;

import com.ubo.common.terminal.InWarehouseMessage;
import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;
import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.pojo.ClientInWareHouseBody;
import dudu.service.pojo.ClientInWareHouseMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.ConcurrentHashMap;


public class InWarehouseMessageHandler extends SimpleMessageHandler implements ProtocolHandler {

	private static final Logger LOG=LoggerFactory.getLogger(InWarehouseMessageHandler.class);

	public InWarehouseMessageHandler() {
		super(LOG);
	}
	
	public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
		throws Exception {
		
		if (!(message instanceof InWarehouseMessage)) {
			LOG.error(String.format(
				"channel [%s]: Invalid Status Message!",
				ctx.channel().id().asShortText()));
			return;
		}
		InWarehouseMessage responMessage = (InWarehouseMessage) message;

		ConcurrentHashMap<String, Channel> channelMap =
				AccessServer.getInstance().getChannelMap();

		String sessionToken = responMessage.getAuthCode();
		if (channelMap.containsKey(sessionToken)) {
			ChannelPipeline pipeline = ctx.pipeline();
			SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
			session.setToken(sessionToken);

			channelMap.put(sessionToken, ctx.channel());
		}else{
			LOG.info("本次请求未登录token{},请先登录",sessionToken);
			return;
		}

		//发送消息到业务模块
		ClientInWareHouseMessage clientInWareHouseMessage = new ClientInWareHouseMessage();
		ClientInWareHouseBody clientInWareHouseBody = new ClientInWareHouseBody();
		BeanUtils.copyProperties(responMessage,clientInWareHouseBody);

		clientInWareHouseMessage.setServiceType(responMessage.getTid());
		clientInWareHouseMessage.setFormatVersion(responMessage.getDeviceVersion());
		clientInWareHouseMessage.setDeviceType(Integer.parseInt(responMessage.getDeviceType()));
		clientInWareHouseMessage.setSerialNumber(responMessage.getSerial());
		clientInWareHouseMessage.setMessageType(responMessage.getCmd());
		clientInWareHouseMessage.setSendTime(responMessage.getSerial().substring(0, 14));
		clientInWareHouseMessage.setSessionToken(sessionToken);
		clientInWareHouseMessage.setMessageBody(clientInWareHouseBody);
		JSONObject jsonObject = JSONObject.fromObject(clientInWareHouseMessage);
		String loginMessage = jsonObject.toString();
		sendMessage("object/" + responMessage.getType(), loginMessage);
		LOG.info("入库消息发送成功{}", loginMessage);
	}
}
