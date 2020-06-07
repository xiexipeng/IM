package im.command;

import im.packet.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午6:14
 * @Description:
 */
public class SendGroupMessageCommand implements ConsoleCommad {

	@Override
	public void exec(Scanner scanner, Channel channel) {
		System.out.println("请输入需要发送的群消息ID:");
		Long groupId = Long.valueOf(scanner.next());
		System.out.println("请输入需要发送的群消息内容:");
		String message = scanner.next();

		GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
		groupMessageRequestPacket.setGroupId(groupId);
		groupMessageRequestPacket.setMessage(message);
		channel.writeAndFlush(groupMessageRequestPacket);
	}
}