package com.ubo.terminal.handler;

import com.ubo.common.terminal.UboLoginMessage;
import dudu.service.pojo.AuthCodeMessage;
import dudu.service.pojo.AuthCodeMessageBody;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.terminal.AccessServer;
import com.ubo.terminal.SessionChannelHandler;

import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.core.utils.Utils;


public class LoginMessageHandler extends SimpleMessageHandler
        implements ProtocolHandler {

    private static final Logger LOG =
            LoggerFactory.getLogger(LoginMessageHandler.class);

    public LoginMessageHandler() {
        super(LOG);
    }

    public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
            throws Exception {

        if (!(message instanceof UboLoginMessage)) {
            LOG.error(String.format(
                    "channel [%s]: Invalid Login Message, class: %s.",
                    ctx.channel().id().asShortText(),
                    message.getClass().getName()));
            return;
        }

        UboLoginMessage loginMsg = (UboLoginMessage) message;

        ChannelGroup channelGroup = AccessServer.getInstance().getChannelGroup();
        ConcurrentHashMap<String, Channel> channelMap =
                AccessServer.getInstance().getChannelMap();
        String sessionToken= loginMsg.getDeviceName().hashCode()+"";
        if (channelMap.containsKey(sessionToken)) {
            Channel existChannel = (Channel) channelMap.get(sessionToken);

            LOG.warn(String.format(
                    "channel[%s] with the same imei(%s) has been open, " +
                            "it will be closed!",
                    existChannel.id().asShortText(),
                    sessionToken));

            LOG.info("channel group size: {}, channel map size: {}.",
                    channelGroup.size(),
                    channelMap.size());

            if (LOG.isDebugEnabled()) {
                Utils.dumpMap(channelMap);
            }

            ChannelPipeline pipeline = existChannel.pipeline();
            SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
            session.setExitCode((byte) 4);
            existChannel.close();
        }

        ChannelPipeline pipeline = ctx.pipeline();
        SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
        session.setToken(sessionToken);

        channelMap.put(sessionToken, ctx.channel());
        LOG.info("channel map size: {}.", channelMap.size());
        if (LOG.isDebugEnabled()) {
            Utils.dumpMap(channelMap);
        }

        //发送消息到业务模块
        AuthCodeMessage authCodeMessage = new AuthCodeMessage();
        AuthCodeMessageBody authCodeMessageBody=new AuthCodeMessageBody();
        authCodeMessageBody.setUsername(loginMsg.getDeviceName());
        authCodeMessageBody.setCommand(loginMsg.getToken());
        authCodeMessageBody.setLo(loginMsg.getLo());
        authCodeMessageBody.setLa(loginMsg.getLa());

        authCodeMessage.setServiceType(loginMsg.getTid());
        authCodeMessage.setFormatVersion(loginMsg.getDeviceVersion());
        authCodeMessage.setDeviceType(Integer.parseInt(loginMsg.getDeviceType()));
        authCodeMessage.setSerialNumber(loginMsg.getSerial());
        authCodeMessage.setMessageType(loginMsg.getCmd());

        authCodeMessage.setSendTime(loginMsg.getSerial().substring(0,14));
        authCodeMessage.setSessionToken(sessionToken);
        authCodeMessage.setMessageBody(authCodeMessageBody);
        JSONObject jsonObject = JSONObject.fromObject(authCodeMessage);
        String loginMessage = jsonObject.toString();

        sendMessage("object/" + loginMsg.getType(), loginMessage);
        LOG.info("登录消息发送成功{}", loginMessage);
    }
}
