package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午10:04
 * @Description:
 */
@Data
public class HeartBeatResponsePacket extends Packet {

	@Override
	public Byte getCommand() {
		return Command.HEARTBEAT_RESPONSE;
	}
}