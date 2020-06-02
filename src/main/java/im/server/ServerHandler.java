package im.server;

import im.LoginRequestPacket;
import im.LoginResponsePacket;
import im.Packet;
import im.PacketCodeC;
import im.Serializer;
import im.packet.MessageRequestPacket;
import im.packet.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.Serializable;
import java.util.Date;


/**
 * @Author: xiexipeng
 * @Date: 2020/5/31
 * @Time: 下午1:15
 * @Description:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		Packet packet = PacketCodeC.decode(buf);
		if (packet instanceof LoginRequestPacket) {
			LoginResponsePacket responsePacket = new LoginResponsePacket();
			LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
			if (valid(loginRequestPacket)) {
				System.out.println(new Date() + ": 登录成功");
				responsePacket.setCode(1);
				responsePacket.setMsg("登录成功");
				responsePacket.setSuccess(true);
			} else {
				System.out.println(new Date() + ": 登录失败");
				responsePacket.setCode(2);
				responsePacket.setMsg("登录失败，密码错误");
				responsePacket.setSuccess(false);
			}
			ByteBuf resBuf = PacketCodeC.encode(ctx.alloc(), responsePacket);
			ctx.channel().writeAndFlush(resBuf);
		} else if (packet instanceof MessageRequestPacket) {
			MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
			System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
			MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
			messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
			ByteBuf responseByteBuf = PacketCodeC.encode(ctx.alloc(), messageResponsePacket);
			ctx.channel().writeAndFlush(responseByteBuf);
		}
	}

	private boolean valid(LoginRequestPacket loginRequestPacket) {
		if ("123456".equals(loginRequestPacket.getPassword()) && "xxp".equals(loginRequestPacket.getUsername())) {
			return true;
		}
		return false;
	}
}