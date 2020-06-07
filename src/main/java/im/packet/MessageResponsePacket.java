package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/1
 * @Time: 下午11:12
 * @Description:
 */
@Data
public class MessageResponsePacket extends Packet {

	private String fromUserId;

	private String fromUserName;

	private String message;

	@Override
	public Byte getCommand() {
		return Command.MESSAGE_RESPONSE;
	}
}