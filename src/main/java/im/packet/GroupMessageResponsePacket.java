package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午6:13
 * @Description:
 */
@Data
public class GroupMessageResponsePacket extends Packet {

	private Long groupId;

	private String fromUserId;

	private String message;

	@Override
	public Byte getCommand() {
		return Command.GROUP_MESSAGE_RESPONSE;
	}
}