package cn.zhangyis.nettydemo.im.handler;

import cn.zhangyis.nettydemo.im.protocol.request.LoginRequestPacket;
import cn.zhangyis.nettydemo.im.protocol.Packet;
import cn.zhangyis.nettydemo.im.protocol.PacketCodeC;
import cn.zhangyis.nettydemo.im.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.getInstance().decode(byteBuf);


        if (packet != null) {
            if(packet instanceof LoginRequestPacket){
                LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

                LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
                loginResponsePacket.setVersion(packet.getVersion());
                loginResponsePacket.setUserId(loginRequestPacket.getUserId());
                loginResponsePacket.setUserName(loginRequestPacket.getUserName());

                if(valid(loginRequestPacket)){
                    loginResponsePacket.setSuccess(true);
                }else{
                    loginResponsePacket.setSuccess(false);
                    loginResponsePacket.setReason("username or password is faile");
                }

                ByteBuf returnBuffer = PacketCodeC.getInstance().encode(ctx.alloc(), loginResponsePacket);
                ctx.channel().writeAndFlush(returnBuffer);
            }
        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}

