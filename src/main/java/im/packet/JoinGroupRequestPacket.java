package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午4:23
 * @Description:
 */
@Data
public class JoinGroupRequestPacket extends Packet{

	private Long groupId;

	@Override
	public Byte getCommand() {
		return Command.JOIN_GROUP_REQUEST;
	}
}