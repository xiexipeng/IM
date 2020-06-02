package im;

import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/31
 * @Time: 下午2:17
 * @Description:
 */
@Data
public class LoginResponsePacket extends Packet {

	private Integer code;

	private String msg;

	private boolean success;

	@Override
	public Byte getCommand() {
		return Command.LOGIN_RESPONSE;
	}
}