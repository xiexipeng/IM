package im;

import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/30
 * @Time: 下午11:18
 * @Description:
 */
@Data
public abstract class Packet {

	/**
	 * 协议版本号
	 */
	private Byte version = 1;

	/**
	 * 获取指令
	 * @return
	 */
	public abstract Byte getCommand();
}