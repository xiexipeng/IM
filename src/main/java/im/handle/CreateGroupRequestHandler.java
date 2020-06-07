package im.handle;

import com.sun.tools.corba.se.idl.StringGen;
import im.packet.CreateGroupRequestPacket;
import im.packet.CreateGroupResponsePacket;
import im.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午3:35
 * @Description:
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

	private AtomicLong groupIdInc = new AtomicLong(20001);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
		List<String> userList = msg.getGroupUserIdList();
		List<String> userNameList = new ArrayList<>();

		ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
		for (String userId : userList) {
			Channel channel = SessionUtil.getChannel(userId);
			if (channel != null) {
				channelGroup.add(channel);
				userNameList.add(SessionUtil.getSession(channel).getUserName());
			}
		}
		Long groupId = groupIdInc.incrementAndGet();
		SessionUtil.bindSessionGroup(groupId, channelGroup);
		CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
		responsePacket.setGroupId(groupId);
		responsePacket.setSuccess(true);
		responsePacket.setUserNameList(userNameList);
		ctx.channel().writeAndFlush(responsePacket);
		System.out.print("群创建成功，id 为[" + responsePacket.getGroupId() + "], ");
		System.out.println("群里面有：" + responsePacket.getUserNameList());
	}
}