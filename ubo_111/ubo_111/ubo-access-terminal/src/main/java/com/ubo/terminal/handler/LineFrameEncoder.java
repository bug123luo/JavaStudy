package com.ubo.terminal.handler;


import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ubo.common.terminal.CommandType;
import com.ubo.common.terminal.UboTerminalMessage;
import dudu.service.pojo.SimpleMessage;
import dudu.service.pojo.UboSimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubo.common.terminal.UboCommandMessage;
import com.ubo.common.terminal.UboCommandMessageEx;
import com.ubo.terminal.InvalidMessageException;
import com.ubo.terminal.SessionChannelHandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

public class LineFrameEncoder extends MessageToMessageEncoder<Object> {

    private static final Logger LOG =
            LoggerFactory.getLogger(LineFrameEncoder.class);

    private final Charset charset;
    private SimpleDateFormat sdf;


    public LineFrameEncoder() {
        charset = CharsetUtil.UTF_8;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {

        if (msg instanceof UboSimpleMessage) {

            UboSimpleMessage message = (UboSimpleMessage) msg;

            ChannelPipeline pipeline = ctx.pipeline();
            SessionChannelHandler.Session session = pipeline.get(SessionChannelHandler.class).getSession();
            String sessionToken = session.getToken();
			
			/*
			String content = String.format("[%s%04d,%s,%s]",
				sdf.format(new Date()), session.generateMsgId(),
				((UboCommandMessage) msg).getCmd().getCode(),
				((UboCommandMessage) msg).getBody());
			*/

            String content = String.format("[%s,%s,%s,%s,%s,%s,%s]",
                    message.getServiceType(),
                    message.getFormatVersion(),
                    message.getDeviceType(),
                    message.getSerialNumber(),
                    message.getMessageType(),
                    message.getMessageBody(),
                    message.getSendTime());

            LOG.debug(String.format("[%s]down msg:(%s->%s).", sessionToken, ((UboSimpleMessage) msg).getSessionToken(), content));

            out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(content.toCharArray()), charset));

        } else {
            throw new InvalidMessageException(String.format(
                    "Invalid encode message type: %s.", msg.getClass().getName()));
        }
    }


    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        UboCommandMessageEx command = new UboCommandMessageEx("888888888888888", CommandType.TRECORD_COMMAND, null);
        command.setTotalPkgNO(1);
        command.setPkgNO(1);
        command.setZip("0");
        command.setZipLength(11);
        command.setZipBody(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        String content = String.format("[%s%04d,%s,%s,%02d,%02d,%04x,",
                sdf.format(new Date()),
                0,
                command.getCmd().getCode(),
                command.getZip(),
                command.getTotalPkgNO(),
                command.getPkgNO(),
                command.getZipLength()
        );

        int len = content.length() + command.getZipLength() + 1;
        ByteBuf buffer = Unpooled.buffer(len);

        byte[] head = content.getBytes();
        buffer.writeBytes(head);

        buffer.writeBytes(command.getZipBody(), 0, command.getZipLength());
        buffer.writeByte(0x5D);
        System.out.println(buffer);

    }
}
