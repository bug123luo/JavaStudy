package com.ubo.terminal;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dudu.service.core.SimpleServer;
import dudu.service.core.utils.Utils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.Timer;
import io.netty.util.concurrent.GlobalEventExecutor;

public class AccessServer extends SimpleServer {

    private static final Logger LOG = LoggerFactory.getLogger(AccessServer.class);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap bootstrap;

    private ChannelGroup channelGroup;
    private ConcurrentHashMap<String, Channel> channelMap;
    @SuppressWarnings("unused")
    private List<ChannelHandler> channelHandlers;

    private String peerAddress;
    private int peerPort;
    private Timer timer;
    private int readTimeoutSeconds;

    private AccessServer(int serverPort, int peerPort) {
        this.port = serverPort;
        this.peerPort = peerPort;
        this.peerAddress = null;
    }

    public static AccessServer getInstance() {
        if (server == null) {
            server = new AccessServer(8122, 11012);
        }

        return (AccessServer) server;
    }

    public ChannelGroup getChannelGroup() {
        return channelGroup;
    }

    public ConcurrentHashMap<String, Channel> getChannelMap() {
        return channelMap;
    }

    public String getPeerAddress() {
        return peerAddress;
    }

    public void setPeerAddress(String peerAddress) {
        this.peerAddress = peerAddress;
    }

    public int getPeerPort() {
        return peerPort;
    }

    public void setPeerPort(int peerPort) {
        this.peerPort = peerPort;
    }

    public List<ChannelHandler> getChannelHandlers() {
        @SuppressWarnings("unchecked")
        List<ChannelHandler> channelHandlers = (List<ChannelHandler>) appContext.getBean("channelHandlers");

        return channelHandlers;
    }

    //public void setChannelHandlers(List<ChannelHandler> channelHandlers) {
    //	this.channelHandlers = channelHandlers;
    //}

    public Timer getTimer() {
        return timer;
    }

    public int getReadTimeoutSeconds() {
        return readTimeoutSeconds;
    }

    public void setReadTimeoutSeconds(int readTimeoutSeconds) {
        this.readTimeoutSeconds = readTimeoutSeconds;
    }

    @Override
    protected void onStart() {

        try {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();

            channelMap = new ConcurrentHashMap<String, Channel>();
            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

            bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            //channel read timeout handler
                            int readTimeoutSeconds = AccessServer.getInstance().getReadTimeoutSeconds();
                            ReadTimeoutHandler timeoutHandler = new ReadTimeoutHandler(readTimeoutSeconds + 5);
                            ch.pipeline().addLast(ReadTimeoutHandler.class.getName(), timeoutHandler);

                            //other user defined handler
                            List<ChannelHandler> handlerChain = AccessServer.getInstance().getChannelHandlers();
                            Iterator<ChannelHandler> iterator = handlerChain.iterator();

                            while (iterator.hasNext()) {
                                ChannelHandler handler = iterator.next();
                                ch.pipeline().addLast(handler.getClass().getName(), handler);
                            }
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_RCVBUF, 1024 * 25)
                    .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)//设置利用内存池解决接收buffer的申请释放时所消耗资源过大问题
            ;

            //.childOption(ChannelOption.MAX_MESSAGES_PER_READ, 1024 * 10);

            //start the server
            ChannelFuture f = bootstrap.bind(port).sync();
            //wait until the server socket is closed
            f.channel().closeFuture().sync();

            LOG.info("Server Started!");

        } catch (Exception e) {
            onStop();
            System.exit(-1);
        }
    }

    @Override
    protected void onStop() {

        LOG.info("onStop()");

        if (channelGroup != null) {
            ChannelGroupFuture future = channelGroup.close();
            future.awaitUninterruptibly();
            channelGroup = null;
            channelMap = null;
            LOG.debug("channels stopped");
        }

        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        LOG.debug("event loop groups shutdown");

        if (appContext != null) {
            appContext.close();
            appContext = null;

            LOG.debug("appContext closed");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOG.error(Utils.getThrowableInfo(e));
        }

        LOG.info("Server Stopped!");

    }

    public static void main(String[] args) {

        try {

            AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("context-beans.xml");

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    AccessServer.getInstance().terminate();
                }
            });

            AccessServer accessSrv = AccessServer.getInstance();
            accessSrv.setAppContext(ctx);

            try {
                Integer serverPort = (Integer) ctx.getBean("serverPort");
                accessSrv.setPort(serverPort.intValue());
            } catch (BeansException e) {
                LOG.warn(e.getMessage(), e.getCause());
            }

            try {
                String peerAddress = (String) ctx.getBean("peerAddress");
                accessSrv.setPeerAddress(peerAddress);
            } catch (BeansException e) {
                LOG.warn(e.getMessage(), e.getCause());

                String localHostAddress = InetAddress.getLocalHost().getHostAddress();
                LOG.debug("Local host address: {}.", localHostAddress);
                accessSrv.setPeerAddress(localHostAddress);
            }

            try {
                Integer peerPort = (Integer) ctx.getBean("peerPort");
                accessSrv.setPeerPort(peerPort);
            } catch (BeansException e) {
                LOG.warn(e.getMessage(), e.getCause());
            }

            try {
                Integer readTimeoutSeconds = (Integer) ctx.getBean("readTimeoutSecondes");
                accessSrv.setReadTimeoutSeconds(readTimeoutSeconds);
            } catch (BeansException e) {
                LOG.warn(e.getMessage(), e.getCause());

                accessSrv.setReadTimeoutSeconds(5 * 60);
            }

            //@SuppressWarnings("unchecked")
            //List<ChannelHandler> channelHandlers = (List<ChannelHandler>)ctx.getBean("channelHandlers");
            //accessSrv.setChannelHandlers(channelHandlers);

            accessSrv.setId("ubo-terminal");

            accessSrv.startup();

        } catch (Exception e) {

            LOG.error(Utils.getThrowableInfo(e));
            AccessServer accessSrv = AccessServer.getInstance();
            if (accessSrv != null) {
                accessSrv.onStop();
            }
        }

    }

}