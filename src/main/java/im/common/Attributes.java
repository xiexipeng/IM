package im.common;

import io.netty.util.AttributeKey;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/1
 * @Time: 下午11:14
 * @Description:
 */
public interface Attributes {
	AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}