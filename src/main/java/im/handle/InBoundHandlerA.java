package im.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午10:53
 * @Description:
 */
public class InBoundHandlerA extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("InBoundHandlerA: " + msg);
		super.channelRead(ctx, msg);
	}
}