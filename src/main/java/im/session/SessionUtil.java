package im.session;

import im.common.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xiexipeng@u51.com
 * @create: 2020/06/06 14:19:06
 * @description:
 * @Version
 **/
public class SessionUtil {

	private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

	private static final Map<Long, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

	public static void bindSession(Session session, Channel channel) {
		userIdChannelMap.put(session.getUserId(), channel);
		channel.attr(Attributes.SESSION).set(session);
	}

	public static void unBindSession(Channel channel) {
		if (hasLogin(channel)) {
			userIdChannelMap.remove(getSession(channel).getUserId());
			channel.attr(Attributes.SESSION).set(null);
		}
	}

	public static void bindSessionGroup(Long groupId, ChannelGroup channelGroup) {
		groupIdChannelGroupMap.put(groupId, channelGroup);
	}

	public static boolean hasLogin(Channel channel) {
		return channel.hasAttr(Attributes.SESSION);
	}

	public static Session getSession(Channel channel) {
		return channel.attr(Attributes.SESSION).get();
	}

	public static Channel getChannel(String userId) {
		return userIdChannelMap.get(userId);
	}

	public static ChannelGroup getChannelGroup(Long groupId) {
		return groupIdChannelGroupMap.get(groupId);
	}
}
