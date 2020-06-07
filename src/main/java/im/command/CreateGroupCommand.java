package im.command;

import im.packet.CreateGroupRequestPacket;
import im.session.Session;
import im.session.SessionUtil;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午3:18
 * @Description:
 */
public class CreateGroupCommand implements ConsoleCommad {
	@Override
	public void exec(Scanner scanner, Channel channel) {
		CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
		System.out.print("【拉人群聊】输入 群聊名称：");
		createGroupRequestPacket.setGroupName(scanner.next());
		System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
		Session session = SessionUtil.getSession(channel);
		String userId = session.getUserId();
		List<String> userList = new ArrayList<>(Arrays.asList(scanner.next().split(",")));
		userList.add(userId);
		createGroupRequestPacket.setGroupUserIdList(userList);
		channel.writeAndFlush(createGroupRequestPacket);
	}
}