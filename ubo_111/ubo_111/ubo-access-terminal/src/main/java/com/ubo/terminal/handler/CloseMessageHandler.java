package com.ubo.terminal.handler;

import com.ubo.common.terminal.ResponsMessage;
import dudu.service.core.utils.Utils;
import dudu.service.pojo.ServerOutWareHouseReplyBody;
import dudu.service.pojo.ServerOutWareHouseReplyMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

import io.netty.channel.group.ChannelGroup;
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

import java.util.concurrent.ConcurrentHashMap;

public class CloseMessageHandler extends SimpleMessageHandler implements ProtocolHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CloseMessageHandler.class);

    public CloseMessageHandler() {
        super(LOG);
    }

    public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
            throws Exception {

        if (!(message instanceof ResponsMessage)) {
            LOG.error(String.format(
                    "channel [%s]: Invalid Close Message!",
                    ctx.channel().id().asShortText()));
            return;
        }

        ResponsMessage responsMessage = (ResponsMessage) message;

        ConcurrentHashMap<String, Channel> channelMap =
                AccessServer.getInstance().getChannelMap();

        String sessionToken = responsMessage.getToken();
        if (channelMap.containsKey(sessionToken)) {
            ChannelPipeline pipeline = ctx.pipeline();
            SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
            session.setToken(sessionToken);

            channelMap.put(sessionToken, ctx.channel());
        }else{
            LOG.info("本次请求未登录token{},请先登录", responsMessage.getToken());
            return;
        }

        ServerOutWareHouseReplyMessage serverOutWareHouseReplyMessage = new ServerOutWareHouseReplyMessage();
        ServerOutWareHouseReplyBody serverOutWareHouseReplyBody=new ServerOutWareHouseReplyBody();
        serverOutWareHouseReplyBody.setReserve(responsMessage.getState());
        serverOutWareHouseReplyBody.setAuthCode(responsMessage.getToken());

        serverOutWareHouseReplyMessage.setServiceType(responsMessage.getTid());
        serverOutWareHouseReplyMessage.setFormatVersion(responsMessage.getDeviceVersion());
        serverOutWareHouseReplyMessage.setDeviceType(Integer.parseInt(responsMessage.getDeviceType()));
        serverOutWareHouseReplyMessage.setSerialNumber(responsMessage.getSerial());
        serverOutWareHouseReplyMessage.setMessageType(responsMessage.getCmd());

        serverOutWareHouseReplyMessage.setSendTime(responsMessage.getSerial().substring(0,14));
        serverOutWareHouseReplyMessage.setSessionToken(responsMessage.getSessionToken());
        serverOutWareHouseReplyMessage.setMessageBody(serverOutWareHouseReplyBody);

        JSONObject jsonObject = JSONObject.fromObject(serverOutWareHouseReplyMessage);
        String serverOutWareHouseReplyMessageStr = jsonObject.toString();

        sendMessage("object/" + responsMessage.getType(), serverOutWareHouseReplyMessageStr);
        LOG.info("04号报文发送成功{}", serverOutWareHouseReplyMessageStr);
    }

}
