package im.handle;

import im.Packet;
import im.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午11:46
 * @Description:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
		PacketCodeC.encode(out,msg);
	}
}