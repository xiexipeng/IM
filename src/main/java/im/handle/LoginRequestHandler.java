package im.handle;

import im.LoginRequestPacket;
import im.LoginResponsePacket;
import im.PacketCodeC;
import im.common.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午11:32
 * @Description:
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = login(ctx, msg);
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private LoginResponsePacket login(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        if (valid(loginRequestPacket)) {
            System.out.println(new Date() + ": 登录成功");
            responsePacket.setCode(1);
            responsePacket.setMsg("登录成功");
            responsePacket.setSuccess(true);
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + ": 登录失败");
            responsePacket.setCode(2);
            responsePacket.setMsg("登录失败，密码错误");
            responsePacket.setSuccess(false);
        }
        return responsePacket;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if ("123456".equals(loginRequestPacket.getPassword()) && "xxp".equals(loginRequestPacket.getUsername())) {
            return true;
        }
        return false;
    }
}