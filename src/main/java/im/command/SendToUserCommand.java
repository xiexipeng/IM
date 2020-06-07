package im.command;

import im.packet.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午3:11
 * @Description:
 */
public class SendToUserCommand implements ConsoleCommad {

	@Override
	public void exec(Scanner scanner, Channel channel) {
		System.out.println("输入需要发送的用户ID: ");
		String toUserId = scanner.next();
		System.out.println("输入需要发送的消息内容: ");
		String msg = scanner.next();
		channel.writeAndFlush(new MessageRequestPacket(toUserId, msg));
	}
}