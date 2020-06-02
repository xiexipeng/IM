package im;

import com.alibaba.fastjson.JSON;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/30
 * @Time: 下午11:32
 * @Description:
 */
public class JSONSerializer implements Serializer {

	@Override
	public byte getSerializeAlgorithm() {
		return SerializeAlgorithm.JSON;
	}

	@Override
	public byte[] serialize(Object object) {
		return JSON.toJSONBytes(object);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, byte[] bytes) {
		return JSON.parseObject(bytes, clazz);
	}
}