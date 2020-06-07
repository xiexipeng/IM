package im.packet;

import im.Command;
import im.Packet;
import lombok.Data;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/1
 * @Time: 下午11:10
 * @Description:
 */
@Data
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}