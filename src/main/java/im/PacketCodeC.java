package im;

import im.packet.CreateGroupRequestPacket;
import im.packet.CreateGroupResponsePacket;
import im.packet.GroupMessageRequestPacket;
import im.packet.GroupMessageResponsePacket;
import im.packet.JoinGroupRequestPacket;
import im.packet.JoinGroupResponsePacket;
import im.packet.MessageRequestPacket;
import im.packet.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/30
 * @Time: 下午11:44
 * @Description:
 */
public class PacketCodeC {

	public static final int MAGIC_NUMBER = 0x12345678;

	public static ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
		ByteBuf buf = byteBufAllocator.ioBuffer();
		byte[] bytes = Serializer.DEFAULT.serialize(packet);

		buf.writeInt(MAGIC_NUMBER);
		buf.writeByte(packet.getVersion());
		buf.writeByte(Serializer.DEFAULT.getSerializeAlgorithm());
		buf.writeByte(packet.getCommand());
		buf.writeInt(bytes.length);
		buf.writeBytes(bytes);
		return buf;
	}

	public static void encode(ByteBuf buf, Packet packet) {
		byte[] bytes = Serializer.DEFAULT.serialize(packet);

		buf.writeInt(MAGIC_NUMBER);
		buf.writeByte(packet.getVersion());
		buf.writeByte(Serializer.DEFAULT.getSerializeAlgorithm());
		buf.writeByte(packet.getCommand());
		buf.writeInt(bytes.length);
		buf.writeBytes(bytes);
	}

	public static Packet decode(ByteBuf byteBuf) {
		byteBuf.skipBytes(4);
		byteBuf.skipBytes(1);
		byte serializeAlgorithm = byteBuf.readByte();
		byte command = byteBuf.readByte();
		int length = byteBuf.readInt();
		byte[] bytes = new byte[length];
		byteBuf.readBytes(bytes);
		Class<? extends Packet> requestType = getRequestPacketType(command);
		Serializer serializer = getSerializer(serializeAlgorithm);
		if (requestType != null && serializer != null) {
			return serializer.deserialize(requestType, bytes);
		}
		return null;
	}

	private static Class<? extends Packet> getRequestPacketType(byte command) {
		if (command == 1) {
			return LoginRequestPacket.class;
		} else if (command == 2) {
			return LoginResponsePacket.class;
		} else if (command == 3) {
			return MessageRequestPacket.class;
		} else if (command == 4) {
			return MessageResponsePacket.class;
		} else if (command == 5) {
			return CreateGroupRequestPacket.class;
		} else if (command == 6) {
			return CreateGroupResponsePacket.class;
		} else if (command == 7) {
			return JoinGroupRequestPacket.class;
		} else if (command == 8) {
			return JoinGroupResponsePacket.class;
		} else if (command == 9) {
			return GroupMessageRequestPacket.class;
		} else if (command == 10) {
			return GroupMessageResponsePacket.class;
		}
		return null;
	}

	private static Serializer getSerializer(byte serializeAlgorithm) {
		if (serializeAlgorithm == 1) {
			return Serializer.DEFAULT;
		}
		return null;
	}
}