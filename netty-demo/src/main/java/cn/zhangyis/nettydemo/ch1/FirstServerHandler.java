package cn.zhangyis.nettydemo.ch1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Date;

@Slf4j
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes("服务端收到客户端的连接请求".getBytes(Charset.forName("UTF-8")));

        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        log.info(new Date()+"服务端获取数据"+buf.toString(Charset.forName("UTF-8")));

        ctx.channel().writeAndFlush(getByteBuffer(ctx));

    }

    private ByteBuf getByteBuffer(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "服务端收到消息--------------------------------".getBytes(Charset.forName("UTF-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }

}
