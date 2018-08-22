package com.ubo.terminal.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

import io.netty.handler.codec.json.JsonObjectDecoder;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.core.terminal.CloseMessage;

import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;

public class CloseMessageHandler extends SimpleMessageHandler implements ProtocolHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CloseMessageHandler.class);

    public CloseMessageHandler() {
        super(LOG);
    }

    public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
            throws Exception {

        if (!(message instanceof CloseMessage)) {
            LOG.error(String.format(
                    "channel [%s]: Invalid Close Message!",
                    ctx.channel().id().asShortText()));
            return;
        }

        CloseMessage closeMsg = (CloseMessage) message;

        //send to login server
        ChannelPipeline pipeline = ctx.pipeline();
        SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
        long sessionMsgId = session.generateMsgId();
        String sessionToken = closeMsg.getSessionToken();
        String jsonObject = JSONObject.fromObject(closeMsg).toString();
        sendMessage(
                "object/" + closeMsg.getType(), jsonObject);
    }

}
