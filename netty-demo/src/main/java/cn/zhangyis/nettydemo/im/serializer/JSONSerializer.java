package cn.zhangyis.nettydemo.im.serializer;

import cn.zhangyis.nettydemo.im.constant.SerializerAlgorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONSerializer implements Serializer{
    @Override
    public byte getSerializedAlgorithm() {
        return SerializerAlgorithm.JSON_JACK;
    }

    @Override
    public byte[] getSerialized(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException {
        return new ObjectMapper().readValue(bytes,clazz);
    }
}
