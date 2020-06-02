package io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/16
 * @Time: 下午9:13
 * @Description:
 */
public class Client {

	public static void main(String[] args) {
		new Thread(()->{
			try {
				Socket socket = new Socket("localhost",8081);
				while (true){
					socket.getOutputStream().write((new Date() + ": hello world").getBytes());
					Thread.sleep(2000);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}