package im.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午10:56
 * @Description:
 */
public class InBoundHandlerB extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("InBoundHandlerB: " + msg);
		super.channelRead(ctx, msg);
	}
}