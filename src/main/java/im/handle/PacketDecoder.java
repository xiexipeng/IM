package im.handle;

import im.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午11:25
 * @Description:
 */
public class PacketDecoder extends ByteToMessageDecoder{
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		out.add(PacketCodeC.decode(in));
	}
}