package im.command;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午3:06
 * @Description:
 */
public class ConsoleCommadManager implements ConsoleCommad {

	private Map<String, ConsoleCommad> consoleCommadMap;

	public ConsoleCommadManager(){
		consoleCommadMap = new HashMap<>();
		consoleCommadMap.put("send",new SendToUserCommand());
		consoleCommadMap.put("group",new CreateGroupCommand());
		consoleCommadMap.put("join",new JoinGroupCommand());
		consoleCommadMap.put("sendgroup",new SendGroupMessageCommand());
	}

	@Override
	public void exec(Scanner scanner, Channel channel) {
		String command = scanner.next();
		ConsoleCommad consoleCommad = consoleCommadMap.get(command);
		if (consoleCommad == null){
			System.err.println("无法识别[" + command + "]指令，请重新输入!");
			return;
		}
		consoleCommad.exec(scanner,channel);
	}
}