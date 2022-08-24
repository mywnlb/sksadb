package cn.zhangyis.nettydemo.ch1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyClient {

    public static final Integer MAX_RETRY_SIZE = 5;


    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap
                //指定线程模型
                .group(workerGroup)
                //指定io为nioSocketChannel
                .channel(NioSocketChannel.class)
                //io处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("客戶端正在启动");
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });


        connect(bootstrap, "127.0.0.1", 8081, MAX_RETRY_SIZE);


    }

    private static void connect(Bootstrap bootstrap, String ipAddress, int port, Integer retry) {


        bootstrap.connect(ipAddress, port)
                .addListener(future -> {

                    if (future.isSuccess()) {
                        System.out.println("客户端连接成功");
                    } else if (retry == 0) {
                        System.out.println("重试次数已用完");
                    } else {
                        //进行重试
                        int order = (MAX_RETRY_SIZE - retry) + 1;

                        int delay = 1 << order;

                        System.out.println(new Date() + "连接失败，第几次重连" + order + " " + delay);


                        bootstrap.config().group().schedule(() ->
                        {
                            int a = retry-1;
                            connect(bootstrap, ipAddress, port, a);
                        }, delay, TimeUnit.SECONDS);
                    }
                });

    }
}
