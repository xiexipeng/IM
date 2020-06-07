package im.handle;

import im.packet.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午6:26
 * @Description:
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
		System.out.println("收到来自群: "+msg.getGroupId()+"用户: "+msg.getFromUserId()+"的消息: "+msg.getMessage());
	}
}