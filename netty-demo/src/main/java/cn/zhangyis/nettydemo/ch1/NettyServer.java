package cn.zhangyis.nettydemo.ch1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;


public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //服务端channel定义属性
        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "neetyServer");

        //每个连接自定义属性
        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientKey");

        //服务端channel配置属性
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);

        //每个连接设置tcp参数
        serverBootstrap
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);

        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        System.out.println("服务端启动中");
                    }
                })
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("处理连接数据");
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });

        bind(serverBootstrap, 8080);

    }

    private static void bind(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port)
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("服务端绑定成功，端口为:" + port);
                        } else {
                            System.out.println("服务端绑定失败，端口为:" + port);
                            bind(serverBootstrap, port + 1);
                        }
                    }
                });
    }
}
