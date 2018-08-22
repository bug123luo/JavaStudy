package com.ubo.terminal.handler;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;

import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.core.terminal.LogoutMessage;

public class LogoutMessageHandler extends SimpleMessageHandler 
	implements ProtocolHandler {
	
	private static final Logger LOG=
		LoggerFactory.getLogger(LogoutMessageHandler.class);
	
	public LogoutMessageHandler() {
		super(LOG);
	}
	
	public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
		throws Exception {
		
		if (!(message instanceof LogoutMessage)) {
			LOG.error(String.format(
				"Channel [%s]: Invalid Logout Message, class: %s.",
				ctx.channel().id().asShortText(),
				message.getClass().getName()));
			return;
		}
		
		LogoutMessage logoutMsg = (LogoutMessage)message;
		
		LOG.debug("logout handler::type {}", logoutMsg.getType());
		
		SessionChannelHandler.Session session = ctx.channel().pipeline().get(SessionChannelHandler.class).getSession();
		session.setExitCode((byte)1);
		
		String sessionToken = session.getToken();
		long sessionMsgId = session.generateMsgId();
		sendMessage(sessionMsgId,
			logoutMsg,
			"object/" + logoutMsg.getType(),
			sessionToken,
			null,
			null, 
			AccessServer.getInstance().getPeerAddress(),
			AccessServer.getInstance().getId());
	}

}
