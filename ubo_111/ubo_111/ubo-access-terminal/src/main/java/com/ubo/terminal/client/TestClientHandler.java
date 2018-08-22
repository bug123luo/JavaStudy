package com.ubo.terminal.client;


import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TestClientHandler extends ChannelInboundHandlerAdapter {



    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        try {
            //m.readUnsignedInt获取ByteBuf当中无符号的32-bit的integer
            //就是我们writeInt的那个时间
            ctx.write("[BTOFFPOSITIONALARM,v0,1,200710231200001000,01,yangqinghui;0000;120;120,20071023120000]");

            System.out.println("客户单===========================");
            if(buffer.isReadable()){
                ByteBuf frame = buffer.readBytes(0);
                byte[] textBytes = new byte[frame.readableBytes()];
                String text = new String(textBytes, "UTF-8");
                System.out.println(text);
            }
            System.out.println("end===========================");
            ctx.close();
        } finally {
            buffer.release();
        }
    }
}

