package im.handle;

import im.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static im.Command.GROUP_CREATE_REQUEST;
import static im.Command.GROUP_MESSAGE_REQUEST;
import static im.Command.JOIN_GROUP_REQUEST;
import static im.Command.MESSAGE_REQUEST;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午7:01
 * @Description:
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

	public static final IMHandler INSTANCE = new IMHandler();

	private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

	private IMHandler() {
		handlerMap = new HashMap<>();

		handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
		handlerMap.put(GROUP_CREATE_REQUEST, CreateGroupRequestHandler.INSTANCE);
		handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
//		handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
//		handlerMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
		handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
//		handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
		handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
	}
}