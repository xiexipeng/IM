package im;

import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/30
 * @Time: 下午11:23
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet {

	private String userId;

	private String username;

	private String password;

	@Override
	public Byte getCommand() {
		return Command.LOGIN_REQUEST;
	}
}