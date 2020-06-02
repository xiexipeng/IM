package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/16
 * @Time: 下午9:03
 * @Description:
 */
public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8080);
		new Thread(() -> {
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					new Thread(() -> {
						int len;
						byte[] data = new byte[1024];
						try {
							InputStream inputStream = socket.getInputStream();
							while ((len = inputStream.read(data)) != -1) {
								System.out.println(new String(data, 0, len));
							}
						} catch (IOException e) {
							e.printStackTrace();
						}

					}).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}