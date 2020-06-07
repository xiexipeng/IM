package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

import java.util.List;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午3:45
 * @Description:
 */
@Data
public class CreateGroupResponsePacket extends Packet {

	private boolean success;

	private long groupId;

	private List<String> userNameList;

	@Override
	public Byte getCommand() {
		return Command.GROUP_CREATE_RESPONSE;
	}
}