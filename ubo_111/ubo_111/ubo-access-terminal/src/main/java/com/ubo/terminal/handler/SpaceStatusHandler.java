package com.ubo.terminal.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.common.terminal.CommandType;
import com.ubo.common.terminal.UboCommandMessage;
import com.ubo.common.terminal.UboSpaceStatusMessage;
import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;

import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.core.utils.Utils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

public class SpaceStatusHandler extends SimpleMessageHandler
	implements ProtocolHandler {

	private static final Logger LOG = 
		LoggerFactory.getLogger(SpaceStatusHandler.class);

	public SpaceStatusHandler() {
		super(LOG);
	}

	@Override
	public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
		throws Exception {
		
		if (!(message instanceof UboSpaceStatusMessage)) {
			LOG.error(String.format(
				"channel [%s]: Invalid SpaceStatus Message!",
				ctx.channel().id().asShortText()));
			return;
		}
		
		UboSpaceStatusMessage spaceStatusMsg = (UboSpaceStatusMessage)message;
		SessionChannelHandler.Session session = ctx.channel().pipeline().get(SessionChannelHandler.class).getSession();
		
		//1 reply terminal
		
		//receive msg OK
		UboCommandMessage replyMsg = new UboCommandMessage(
				spaceStatusMsg.getEqId(),
			CommandType.GENERALREPLY_COMMAND,
			"0");
		
		replyMsg.setMsgId(spaceStatusMsg.getMsgId());
		
		ChannelFuture future = ctx.channel().write(replyMsg);
		
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				if (!future.isSuccess()) {
					LOG.error("Fail to replay space status message!");
					LOG.error(Utils.getThrowableInfo(future.cause()));
				}
			}
			
		});
		
	
		String sessionToken = session.getToken();
		Long sessionMsgId = session.generateMsgId();
		
		//2 send record to logic server
		sendMessage(sessionMsgId,
			spaceStatusMsg,
			"object/" + spaceStatusMsg.getType(),
			sessionToken,
			null,
			(String)null,
			AccessServer.getInstance().getPeerAddress(),
			AccessServer.getInstance().getId());
	
	}
	
}
