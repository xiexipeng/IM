package im.handle;

import im.packet.HeartBeatRequestPacket;
import im.packet.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午10:02
 * @Description:
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
	public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

	private HeartBeatRequestHandler() {

	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
		ctx.writeAndFlush(new HeartBeatResponsePacket());
	}
}