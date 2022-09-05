package cn.zhangyis.nettydemo.im.handler;

import cn.zhangyis.nettydemo.im.protocol.request.LoginRequestPacket;
import cn.zhangyis.nettydemo.im.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelActive");

        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("libo");
        loginRequestPacket.setPassword("123456");

        //获取编码
        ByteBuf encode = PacketCodeC.getInstance().encode(ctx.alloc(), loginRequestPacket);

        //发送数据
        ctx.channel().writeAndFlush(encode);
    }

}

