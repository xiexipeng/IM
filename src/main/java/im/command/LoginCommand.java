package im.command;

import im.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午3:31
 * @Description:
 */
public class LoginCommand implements ConsoleCommad {

	@Override
	public void exec(Scanner scanner, Channel channel) {
		System.out.print("输入用户名登录: ");
		String userName = scanner.nextLine();
		LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
		loginRequestPacket.setUsername(userName);
		loginRequestPacket.setPassword("123456");
		channel.writeAndFlush(loginRequestPacket);
	}
}