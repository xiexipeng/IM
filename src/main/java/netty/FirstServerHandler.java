package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/30
 * @Time: 下午6:54
 * @Description:
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		System.out.println(new Date() + ": 服务端读到数据 -> " + buf.toString(Charset.forName("utf-8")));
		ByteBuf out = getByteBuf(ctx);
		ctx.channel().writeAndFlush(out);
	}

	private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
		byte[] bytes = "Don't answer!".getBytes(Charset.forName("utf-8"));

		ByteBuf buffer = ctx.alloc().buffer();

		buffer.writeBytes(bytes);

		return buffer;
	}
}