package im.handle;

import im.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午11:58
 * @Description:
 */
public class LoginResponseHander extends SimpleChannelInboundHandler<LoginResponsePacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
		// TODO
	}
}