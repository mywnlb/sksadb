package cn.zhangyis.nettydemo.im.agreement;

import cn.zhangyis.nettydemo.im.constant.SerializerAlgorithm;
import cn.zhangyis.nettydemo.im.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.Objects;

public class PacketCodeC {
    private static final int MAGIC_NUMER = 0X12345678;

    public ByteBuf encode(Packet packet) throws Exception {
        //直接内存不受虚拟机管理
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        byte[] bytes = SerializerAlgorithm.DEFAULT.getSerialized(packet);

        byteBuf.writeInt(MAGIC_NUMER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializerAlgorithm.DEFAULT.getSerializedAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeByte(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) throws Exception {
        //跳过魔数
        byteBuf.skipBytes(4);

        //跳过版本号
        byteBuf.skipBytes(1);

        //获取序列化算法标识
        byte algorithm = byteBuf.readByte();

        //获取指令
        byte command = byteBuf.readByte();

        //获取长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);

        Serializer serializer = getSerialized(algorithm);

        if(Objects.nonNull(requestType) && Objects.nonNull(serializer)){
            return serializer.deserialize(requestType,bytes);
        }
        return null;
    }

}
