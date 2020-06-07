package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午4:24
 * @Description:
 */
@Data
public class JoinGroupResponsePacket extends Packet {

	private boolean success;

	private Long groupId;

	private String msg;

	@Override
	public Byte getCommand() {
		return Command.JOIN_GROUP_RESPONSE;
	}
}