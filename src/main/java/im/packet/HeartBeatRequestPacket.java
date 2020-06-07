package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/7
 * @Time: 下午9:48
 * @Description:
 */
@Data
public class HeartBeatRequestPacket extends Packet{

	@Override
	public Byte getCommand() {
		return Command.HEARTBEAT_REQUEST;
	}
}