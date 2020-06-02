package im.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午11:05
 * @Description:
 */
public class OutBoundHandlerC extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("OutBoundHandlerC: " + msg);
		super.write(ctx, msg, promise);
	}
}