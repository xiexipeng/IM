package im.handle;

import im.packet.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午4:46
 * @Description:
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
		if (msg.isSuccess()) {
			System.out.println("加入群[" + msg.getGroupId() + "]成功!");
		} else {
			System.err.println("加入群[" + msg.getGroupId() + "]失败，原因为：" + msg.getMsg());
		}
	}
}