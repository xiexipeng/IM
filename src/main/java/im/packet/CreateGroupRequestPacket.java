package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

import java.util.List;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午3:21
 * @Description:
 */
@Data
public class CreateGroupRequestPacket extends Packet{

	private String groupName;

	private List<String> groupUserIdList;

	@Override
	public Byte getCommand() {
		return Command.GROUP_CREATE_REQUEST;
	}
}