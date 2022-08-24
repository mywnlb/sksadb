package cn.zhangyis.nettydemo.ch1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端写出数据");


        ByteBuf buffer = getByteBuffer(ctx);

        ctx.channel().writeAndFlush(buffer);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端收到服务端的消息:{}",((ByteBuf) msg).toString(Charset.forName("UTF-8")));
    }

    private ByteBuf getByteBuffer(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = "hello world".getBytes(Charset.forName("UTF-8"));

        buffer.writeBytes(bytes);

        return buffer;
    }
}
