package im.command;

import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommad {

	void exec(Scanner scanner, Channel channel);
}
