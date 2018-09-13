package com.ubo.terminal.handler;

import com.ubo.common.terminal.BasicResponMessage;
import com.ubo.common.terminal.UboHeartbeatMessage;
import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;
import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.core.utils.Utils;
import dudu.service.pojo.ClientOutWareHouseBody;
import dudu.service.pojo.ClientOutWareHouseMessage;
import dudu.service.pojo.UboSimpleMessage;
import io.netty.channel.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;


public class StrapDateRequestMessageHandler extends SimpleMessageHandler implements ProtocolHandler {

	private static final Logger LOG=LoggerFactory.getLogger(StrapDateRequestMessageHandler.class);

	public StrapDateRequestMessageHandler() {
		super(LOG);
	}

	public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
			throws Exception {

		if (!(message instanceof BasicResponMessage)) {
			LOG.error(String.format(
					"channel [%s]: Invalid Status Message!",
					ctx.channel().id().asShortText()));
			return;
		}

		BasicResponMessage responMessage = (BasicResponMessage)message;
		ConcurrentHashMap<String, Channel> channelMap =
				AccessServer.getInstance().getChannelMap();

		String sessionToken = responMessage.getSessionId();
		if (channelMap.containsKey(sessionToken)) {
			ChannelPipeline pipeline = ctx.pipeline();
			SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
			session.setToken(sessionToken);

			channelMap.put(sessionToken, ctx.channel());
		}else{
			LOG.info("本次请求未登录token{},请先登录",sessionToken);
			return;
		}
		//20180823 lcc 添加
		ClientOutWareHouseMessage clientOutWareHouseMessage = new ClientOutWareHouseMessage();
		ClientOutWareHouseBody clientOutWareHouseBody = new ClientOutWareHouseBody();
		clientOutWareHouseBody.setUsername(responMessage.getDeviceName());
		clientOutWareHouseBody.setAuthCode(responMessage.getSessionId());
		clientOutWareHouseBody.setCommand(responMessage.getToken());
		clientOutWareHouseBody.setLa(responMessage.getLg());
		clientOutWareHouseBody.setLo(responMessage.getLo());

		clientOutWareHouseMessage.setServiceType(responMessage.getTid());
		clientOutWareHouseMessage.setFormatVersion(responMessage.getDeviceVersion());
		clientOutWareHouseMessage.setDeviceType(Integer.parseInt(responMessage.getDeviceType()));
		clientOutWareHouseMessage.setSerialNumber(responMessage.getSerial());
		clientOutWareHouseMessage.setMessageType(responMessage.getCmd());
		clientOutWareHouseMessage.setSendTime(responMessage.getSerial().substring(0, 14));
		clientOutWareHouseMessage.setSessionToken(sessionToken);
		clientOutWareHouseMessage.setMessageBody(clientOutWareHouseBody);

		JSONObject jsonObject = JSONObject.fromObject(clientOutWareHouseMessage);
		String outWareHouseMessage = jsonObject.toString();
		sendMessage("object/" + responMessage.getType(), outWareHouseMessage);
		LOG.info("腕表出库消息发送成功{}", outWareHouseMessage);

	}
}
