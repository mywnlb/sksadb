package cn.zhangyis.nettydemo.im.constant;

import cn.zhangyis.nettydemo.im.serializer.JSONSerializer;
import cn.zhangyis.nettydemo.im.serializer.Serializer;

/**
 * 序列化格式
 */
public interface SerializerAlgorithm {
    byte JSON_JACK = 1;

    Serializer DEFAULT = new JSONSerializer();
}
