package im.handle;

import im.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午11:32
 * @Description:
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
		// TODO 登录逻辑
	}
}