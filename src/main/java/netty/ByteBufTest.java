package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.charset.Charset;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/30
 * @Time: 下午7:49
 * @Description:
 */
public class ByteBufTest {

	public static void main(String[] args) {
		ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);

		print("allocate ByteBuf(9, 100)", buffer);

		buffer.writeBytes(new byte[]{1, 2, 3, 4});
		print("writeBytes(1,2,3,4)", buffer);

		buffer.writeInt(12);
		print("writeInt(12)", buffer);
//		System.out.println(buffer.toString(Charset.forName("utf-8")));

		// write 方法改变写指针, 写完之后写指针等于 capacity 的时候，buffer 不可写
		buffer.writeBytes(new byte[]{5});
		print("writeBytes(5)", buffer);

		buffer.writeBytes(new byte[]{6});
		print("writeBytes(6)", buffer);

		// get 方法不改变读写指针
		System.out.println("getByte(3) return: " + buffer.getByte(3));
		System.out.println("getShort(3) return: " + buffer.getShort(3));
		System.out.println("getInt(3) return: " + buffer.getInt(3));
		print("getByte()", buffer);

		// set 方法不改变读写指针
		buffer.setByte(buffer.readableBytes() + 1, 0);
		print("setByte()", buffer);

		// read 方法改变读指针
		byte[] dst = new byte[buffer.readableBytes()];
		buffer.readBytes(dst);
		print("readBytes(" + dst.length + ")", buffer);
	}

	private static void print(String action, ByteBuf buffer) {
		System.out.println("after ===========" + action + "============");
		System.out.println("capacity(): " + buffer.capacity());
		System.out.println("maxCapacity(): " + buffer.maxCapacity());
		System.out.println("readerIndex(): " + buffer.readerIndex());
		System.out.println("readableBytes(): " + buffer.readableBytes());
		System.out.println("isReadable(): " + buffer.isReadable());
		System.out.println("writerIndex(): " + buffer.writerIndex());
		System.out.println("writableBytes(): " + buffer.writableBytes());
		System.out.println("isWritable(): " + buffer.isWritable());
		System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
		System.out.println();
	}
}