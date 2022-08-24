package cn.zhangyis.nettydemo.im.agreement;

import lombok.Data;

import static cn.zhangyis.nettydemo.im.agreement.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet{
    private Integer userId;
    private String password;
    private String userName;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
