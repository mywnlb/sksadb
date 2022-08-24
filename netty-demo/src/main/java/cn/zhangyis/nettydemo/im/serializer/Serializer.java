package cn.zhangyis.nettydemo.im.serializer;

public interface Serializer {
    /**
     * 序列化算法
     * @return
     */
    byte getSerializedAlgorithm();

    /**
     * java对象转二进制数据
     * @param obj
     * @return
     */
    byte[] getSerialized(Object obj);

    /**
     * 二进制转java对象
     * @param clazz
     * @param bytes
     * @return
     * @param <T>
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes);

}
