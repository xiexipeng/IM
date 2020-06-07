package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午6:09
 * @Description:
 */
@Data
public class GroupMessageRequestPacket extends Packet {

	private Long groupId;

	private String message;

	@Override
	public Byte getCommand() {
		return Command.GROUP_MESSAGE_REQUEST;
	}
}