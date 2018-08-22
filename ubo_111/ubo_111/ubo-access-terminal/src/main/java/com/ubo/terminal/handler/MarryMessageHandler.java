package com.ubo.terminal.handler;

import com.ubo.common.terminal.MarryRequestMessage;
import com.ubo.terminal.SessionChannelHandler;
import dudu.service.core.MessageBean;
import dudu.service.core.ProtocolHandler;
import dudu.service.core.SimpleMessageHandler;
import dudu.service.pojo.ClientDeviceBindingBody;
import dudu.service.pojo.ClientDeviceBindingMessage;
import io.netty.channel.ChannelHandlerContext;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MarryMessageHandler extends SimpleMessageHandler implements ProtocolHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MarryMessageHandler.class);

    public MarryMessageHandler() {
        super(LOG);
    }

    public void handleMessage(ChannelHandlerContext ctx, MessageBean message)
            throws Exception {

        if (!(message instanceof MarryRequestMessage)) {
            LOG.error(String.format(
                    "channel [%s]: Invalid Status Message!",
                    ctx.channel().id().asShortText()));
            return;
        }

        MarryRequestMessage responMessage = (MarryRequestMessage) message;
        SessionChannelHandler.Session session = ctx.channel().pipeline().get(SessionChannelHandler.class).getSession();

        /*//1 reply terminal
        UboSimpleMessage replyMsg = new UboSimpleMessage();
        replyMsg.setServiceType(responMessage.getTid());
        replyMsg.setFormatVersion(responMessage.getDeviceVersion());
        replyMsg.setDeviceType(Integer.parseInt(responMessage.getDeviceType()));
        replyMsg.setSerialNumber(responMessage.getSerial());
        replyMsg.setMessageType("06");
        replyMsg.setMessageBody("0@" + session.getToken());
        replyMsg.setSendTime(responMessage.getSerial().substring(0, 14));
        ChannelFuture future = ctx.channel().write(replyMsg);

        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    LOG.error("Fail to replay heartbeat message!");
                    LOG.error(Utils.getThrowableInfo(future.cause()));
                }
            }
        });*/
        ClientDeviceBindingMessage authCodeMessage = new ClientDeviceBindingMessage();
        //发送消息到业务模块
        ClientDeviceBindingBody clientDeviceBindingBody = new ClientDeviceBindingBody();
        clientDeviceBindingBody.setReserve(responMessage.getReserve());
        clientDeviceBindingBody.setBluetoothMac(responMessage.getGunId());
        clientDeviceBindingBody.setBindTime(responMessage.getTime());
        clientDeviceBindingBody.setLo(responMessage.getLo());
        clientDeviceBindingBody.setLa(responMessage.getLg());
        clientDeviceBindingBody.setFailReason(responMessage.getFailType());
        clientDeviceBindingBody.setAuthCode(responMessage.getSessionId());

        authCodeMessage.setServiceType(responMessage.getTid());
        authCodeMessage.setFormatVersion(responMessage.getDeviceVersion());
        authCodeMessage.setDeviceType(Integer.parseInt(responMessage.getDeviceType()));
        authCodeMessage.setSerialNumber(responMessage.getSerial());
        authCodeMessage.setMessageType(responMessage.getCmd());
        authCodeMessage.setSendTime(responMessage.getSerial().substring(0, 14));
        authCodeMessage.setSessionToken(session.getToken());
        authCodeMessage.setMessageBody(responMessage);
        authCodeMessage.setMessageBody(clientDeviceBindingBody);
        JSONObject jsonObject = JSONObject.fromObject(authCodeMessage);
        String loginMessage = jsonObject.toString();
        sendMessage("object/" + responMessage.getType(), loginMessage);
        LOG.info("绑定消息发送成功{}", loginMessage);
    }
}
