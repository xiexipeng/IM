package im.command;

import im.packet.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午4:21
 * @Description:
 */
public class JoinGroupCommand implements ConsoleCommad {

	@Override
	public void exec(Scanner scanner, Channel channel) {
		System.out.print("输入 groupId，加入群聊：");
		Long groupId = Long.valueOf(scanner.next());
		JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
		joinGroupRequestPacket.setGroupId(groupId);
		channel.writeAndFlush(joinGroupRequestPacket);
	}
}