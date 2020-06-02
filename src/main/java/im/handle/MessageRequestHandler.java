package im.handle;

import im.packet.MessageRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午11:41
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
		// TODO 消息解析
	}
}