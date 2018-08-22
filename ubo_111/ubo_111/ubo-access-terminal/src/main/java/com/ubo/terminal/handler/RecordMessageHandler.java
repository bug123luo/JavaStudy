package com.ubo.terminal.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.common.terminal.CommandType;
import com.ubo.common.terminal.UboCommandMessage;
import com.ubo.common.terminal.UboRecordMessage;
import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;

import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.core.utils.Utils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

public class RecordMessageHandler extends SimpleMessageHandler
	implements ProtocolHandler {

	private static final Logger LOG = 
		LoggerFactory.getLogger(PassthroughWithoutAckHandler.class);

	public RecordMessageHandler() {
		super(LOG);
	}

	@Override
	public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
		throws Exception {
		
		if (!(message instanceof UboRecordMessage)) {
			LOG.error(String.format(
				"channel [%s]: Invalid Record Message!",
				ctx.channel().id().asShortText()));
			return;
		}
		
		UboRecordMessage recordMsg = (UboRecordMessage)message;
		SessionChannelHandler.Session session = ctx.channel().pipeline().get(SessionChannelHandler.class).getSession();
		
		//1 reply terminal
		
		//uncompress OK
		UboCommandMessage replyMsg = new UboCommandMessage(
			recordMsg.getEqId(),
			CommandType.TRECORD_RESP_COMMAND,
			 String.format("%d@%d@0", recordMsg.getTotalPkgNo(), recordMsg.getPkgNo()));
		// reply record serialno 
		replyMsg.setMsgId(recordMsg.getMsgId());
		
		ChannelFuture future = ctx.channel().write(replyMsg);
		
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				if (!future.isSuccess()) {
					LOG.error("Fail to replay record message!");
					LOG.error(Utils.getThrowableInfo(future.cause()));
				}
			}
			
		});
		
		String sessionToken = session.getToken();
		//2 send record to logic server
		Long sessionMsgId = session.generateMsgId();
		sendMessage(sessionMsgId,
			recordMsg,
			"object/" + recordMsg.getType(),
			sessionToken,
			null,
			(String)null,
			AccessServer.getInstance().getPeerAddress(),
			AccessServer.getInstance().getId());
	}

}
