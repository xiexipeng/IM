package im.client;

import im.LoginRequestPacket;
import im.LoginResponsePacket;
import im.Packet;
import im.PacketCodeC;
import im.common.LoginUtil;
import im.packet.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/31
 * @Time: 下午1:14
 * @Description:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(new Date() + ": 客户端开始登录");
		LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
		loginRequestPacket.setUserId(UUID.randomUUID().toString());
		loginRequestPacket.setUsername("xxp");
		loginRequestPacket.setPassword("123456");
		ByteBuf byteBuf = PacketCodeC.encode(ctx.alloc(), loginRequestPacket);
		ctx.channel().writeAndFlush(byteBuf);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		Packet packet = PacketCodeC.decode(buf);
		if (packet instanceof LoginResponsePacket) {
			LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
			if (loginResponsePacket.isSuccess()) {
				LoginUtil.markAsLogin(ctx.channel());
				System.out.println(new Date() + ": 客户端登录成功");
			} else {
				System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getMsg());

			}
		}else if (packet instanceof MessageResponsePacket){
			MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
			System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
		}
	}
}