package im.handle;

import im.LoginRequestPacket;
import im.LoginResponsePacket;
import im.PacketCodeC;
import im.common.LoginUtil;
import im.session.Session;
import im.session.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/2
 * @Time: 下午11:32
 * @Description:
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private static AtomicInteger userIdInc = new AtomicInteger(10001);

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = login(ctx, msg);
        String userId = String.valueOf(userIdInc.incrementAndGet());
        loginResponsePacket.setUserId(userId);
        SessionUtil.bindSession(new Session(userId, msg.getUsername()), ctx.channel());
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }

    private LoginResponsePacket login(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        if (valid(loginRequestPacket)) {
            System.out.println(new Date() + ": 用户" + loginRequestPacket.getUsername() + ": 登录成功");
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
        if ("123456".equals(loginRequestPacket.getPassword())) {
            return true;
        }
        return false;
    }
}