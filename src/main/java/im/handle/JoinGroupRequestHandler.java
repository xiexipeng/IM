package im.handle;

import im.packet.JoinGroupRequestPacket;
import im.packet.JoinGroupResponsePacket;
import im.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午4:39
 * @Description:
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
		JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
		Long groupId = msg.getGroupId();
		ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
		if (channelGroup == null) {
			System.out.println("群聊id: " + groupId + "不存在");
			joinGroupResponsePacket.setSuccess(false);
			joinGroupResponsePacket.setMsg("群聊id: " + groupId + "不存在");
			ctx.channel().writeAndFlush(joinGroupResponsePacket);
		}else {
			channelGroup.add(ctx.channel());
			joinGroupResponsePacket.setGroupId(groupId);
			joinGroupResponsePacket.setSuccess(true);
			ctx.channel().writeAndFlush(joinGroupResponsePacket);
		}

	}
}