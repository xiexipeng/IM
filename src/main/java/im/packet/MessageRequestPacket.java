package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/1
 * @Time: 下午11:10
 * @Description:
 */
@Data
public class MessageRequestPacket extends Packet {

	private String message;

	@Override
	public Byte getCommand() {
		return Command.MESSAGE_REQUEST;
	}
}