package cn.zhangyis.nettydemo.im.protocol.request;

import cn.zhangyis.nettydemo.im.protocol.Packet;
import lombok.Data;

import static cn.zhangyis.nettydemo.im.constant.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String password;
    private String userName;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
