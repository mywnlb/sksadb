package cn.zhangyis.nettydemo.im.protocol.response;

import cn.zhangyis.nettydemo.im.protocol.Packet;
import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return null;
    }
}
