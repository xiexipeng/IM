package im.common;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/1
 * @Time: 下午11:21
 * @Description:
 */
public class LoginUtil {

	public static void markAsLogin(Channel channel){
		channel.attr(Attributes.LOGIN).set(true);
	}

	public static boolean hasLogin(Channel channel){
		Attribute<Boolean> attribute = channel.attr(Attributes.LOGIN);
		return attribute.get() != null;
	}
}