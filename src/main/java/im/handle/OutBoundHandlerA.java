package im.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午11:04
 * @Description:
 */
public class OutBoundHandlerA extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("OutBoundHandlerA: " + msg);
		super.write(ctx, msg, promise);
	}
}