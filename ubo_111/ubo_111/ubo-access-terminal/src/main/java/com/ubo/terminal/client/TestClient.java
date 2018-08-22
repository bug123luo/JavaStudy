package com.ubo.terminal.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TestClient {

    public static void main(String[] args) throws Exception {
        //Group：群组，Loop：循环，Event：事件，这几个东西联在一起，相比大家也大概明白它的用途了。
        //Netty内部都是通过线程在处理各种数据，EventLoopGroup就是用来管理调度他们的，注册Channel，管理他们的生命周期。
        //如果你只使用了一个EventLoopGroup，它将既被用于boss group又被用于worker group
        //boss group是不被用于客户端的
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //Bootstrap客户端用于简单建立Channel
            //childOption不能用于Bootstrap
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            //NioSocketChannel用于客户端创建Channel
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    //指定使用的数据处理方式
                    ch.pipeline().addLast(new TestClientHandler());
                }
            });
            //客户端开始连接
            ChannelFuture f = b.connect("localhost", 9876).sync();
            //等待直到这个连接被关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}




