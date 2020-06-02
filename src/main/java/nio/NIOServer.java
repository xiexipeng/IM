package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/16
 * @Time: 下午9:53
 * @Description:
 */
public class NIOServer {

	public static void main(String[] args) throws IOException {
		Selector serverSelector = Selector.open();
		Selector clientSelector = Selector.open();

		new Thread(() -> {
			try {
				ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
				serverSocketChannel.socket().bind(new InetSocketAddress(8081));
				serverSocketChannel.configureBlocking(false);
				serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

				while (true) {
					if (serverSelector.select(1) > 0) {
						Set<SelectionKey> set = serverSelector.selectedKeys();
						Iterator<SelectionKey> keyIterator = set.iterator();
						while (keyIterator.hasNext()) {
							SelectionKey key = keyIterator.next();
							if (key.isAcceptable()) {
								try {
									SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
									clientChannel.configureBlocking(false);
									clientChannel.register(clientSelector, SelectionKey.OP_READ);
								} finally {
									keyIterator.remove();
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			while (true) {
				try {
					if (clientSelector.select(1) > 0) {
						Set<SelectionKey> set = clientSelector.selectedKeys();
						Iterator<SelectionKey> keyIterator = set.iterator();
						while (keyIterator.hasNext()) {
							SelectionKey key = keyIterator.next();
							if (key.isReadable()) {
								try {
									SocketChannel clientChannel = (SocketChannel) key.channel();
									ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
									clientChannel.read(byteBuffer);
									byteBuffer.flip();
									System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
								} finally {
									keyIterator.remove();
									key.interestOps(SelectionKey.OP_READ);
								}
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}