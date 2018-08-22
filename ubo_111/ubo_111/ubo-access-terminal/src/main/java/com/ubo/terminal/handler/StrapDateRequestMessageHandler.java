package com.ubo.terminal.handler;

import com.ubo.common.terminal.BasicResponMessage;
import com.ubo.common.terminal.UboHeartbeatMessage;
import com.ubo.terminal.SessionChannelHandler;
import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.core.utils.Utils;
import dudu.service.pojo.UboSimpleMessage;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
		SessionChannelHandler.Session session = ctx.channel().pipeline().get(SessionChannelHandler.class).getSession();

		//1 reply terminal
		UboSimpleMessage replyMsg = new UboSimpleMessage();
		replyMsg.setServiceType(responMessage.getTid());
		replyMsg.setFormatVersion(responMessage.getDeviceVersion());
		replyMsg.setDeviceType(Integer.parseInt(responMessage.getDeviceType()));
		replyMsg.setSerialNumber(responMessage.getSerial());
		replyMsg.setMessageType("06");
		replyMsg.setMessageBody("0@1111111@2222222@20180805000000@20180807000000@4@5@6@7@8@9@10@20180805000000@12@13");
		replyMsg.setSendTime(responMessage.getSerial().substring(0,14));
		ChannelFuture future = ctx.channel().write(replyMsg);
		
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				if (!future.isSuccess()) {
					LOG.error("Fail to replay heartbeat message!");
					LOG.error(Utils.getThrowableInfo(future.cause()));
				}
			}
		});
	}
}
