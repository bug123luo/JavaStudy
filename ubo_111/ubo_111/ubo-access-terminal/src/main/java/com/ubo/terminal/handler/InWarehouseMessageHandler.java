package com.ubo.terminal.handler;

import com.ubo.common.terminal.InWarehouseMessage;
import com.ubo.terminal.SessionChannelHandler;
import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.pojo.ClientInWareHouseBody;
import dudu.service.pojo.ClientInWareHouseMessage;
import io.netty.channel.ChannelHandlerContext;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;


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
		SessionChannelHandler.Session session = ctx.channel().pipeline().get(SessionChannelHandler.class).getSession();

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
		clientInWareHouseMessage.setSessionToken(session.getToken());
		clientInWareHouseMessage.setMessageBody(clientInWareHouseBody);
		JSONObject jsonObject = JSONObject.fromObject(clientInWareHouseMessage);
		String loginMessage = jsonObject.toString();
		sendMessage("object/" + responMessage.getType(), loginMessage);
		LOG.info("入库消息发送成功{}", loginMessage);
	}
}
