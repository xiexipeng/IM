package im.handle;

import im.packet.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午9:44
 * @Description: 定时心跳
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

	private static final int HEARTBEAT_INNTERVAL = 5;


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		scheduleSendHeartBeat(ctx);
		super.channelActive(ctx);
	}

	private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
		ctx.executor().schedule(() -> {
			if (ctx.channel().isActive()) {
				ctx.writeAndFlush(new HeartBeatRequestPacket());
				scheduleSendHeartBeat(ctx);
			}
		}, HEARTBEAT_INNTERVAL, TimeUnit.SECONDS);
	}
}