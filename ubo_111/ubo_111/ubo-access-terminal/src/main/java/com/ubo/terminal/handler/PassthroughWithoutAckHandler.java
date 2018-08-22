package com.ubo.terminal.handler;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;

import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;

public class PassthroughWithoutAckHandler extends SimpleMessageHandler
	implements ProtocolHandler {

	private static final Logger LOG=
		LoggerFactory.getLogger(PassthroughWithoutAckHandler.class);
	
	public PassthroughWithoutAckHandler() {
		super(LOG);
	}
	
	public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
		throws Exception {
		
		SessionChannelHandler.Session session = ctx.channel().pipeline().get(SessionChannelHandler.class).getSession();
		String sessionToken = session.getToken();
		long sessionMsgId = session.generateMsgId();
		
		sendMessage(sessionMsgId,
			message,
			"object/" + message.getType(),
			sessionToken,
			null,
			(String)null,
			AccessServer.getInstance().getPeerAddress(),
			AccessServer.getInstance().getId());
	}

	
}
