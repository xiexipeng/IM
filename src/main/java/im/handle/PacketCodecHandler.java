package im.handle;

import im.Packet;
import im.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午6:51
 * @Description: 编解码
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

	public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

	private PacketCodecHandler() {

	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
		ByteBuf buf = ctx.channel().alloc().ioBuffer();
		PacketCodeC.encode(buf, msg);
		out.add(buf);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		out.add(PacketCodeC.decode(msg));
	}
}