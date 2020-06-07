package im.handle;

import im.packet.GroupMessageRequestPacket;
import im.packet.GroupMessageResponsePacket;
import im.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午6:20
 * @Description:
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
		Long groupId = msg.getGroupId();
		GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
		groupMessageResponsePacket.setFromUserId(SessionUtil.getSession(ctx.channel()).getUserId());
		groupMessageResponsePacket.setGroupId(groupId);
		groupMessageResponsePacket.setMessage(msg.getMessage());

		ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
		channelGroup.writeAndFlush(groupMessageResponsePacket);
	}
}