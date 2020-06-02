package im.netty;

import im.PacketCodeC;
import im.client.ClientHandler;
import im.common.LoginUtil;
import im.packet.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.FirstClientHandler;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/30
 * @Time: 下午5:51
 * @Description:
 */
public class NettyClient {

	private final static int MAX_RETRY = 3;

	public static void main(String[] args) {
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap();
		bootstrap
				// 1.指定线程模型
				.group(workerGroup)
				// 2.指定 IO 类型为 NIO
				.channel(NioSocketChannel.class)
				// 3.IO 处理逻辑
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) {
						ch.pipeline().addLast(new ClientHandler());
					}
				});
		// 4.建立连接
		connect(bootstrap,"localhost",8080,MAX_RETRY);
	}

	/**
	 *
	 * @param bootstrap
	 * @param host
	 * @param port
	 * @param retry:剩余重试次数
	 */
	private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
		bootstrap.connect(host, port).addListener(future -> {
			if (future.isSuccess()) {
				System.out.println("连接成功!");
				Channel channel = ( (ChannelFuture) future).channel();
				startConsoleThread(channel);
			} else if (retry == 0) {
				System.err.println("重试次数已用完，放弃连接！");
			} else {
				// 第几次重连
				int order = (MAX_RETRY - retry) + 1;
				// 本次重连的间隔
				int delay = 1 << order;
				System.out.println(new Date() + ": 连接失败，第" + order + "次重连……");
				bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
						.SECONDS);
			}
		});
	}

	private static void startConsoleThread(Channel channel){
		new Thread(()->{
			while (!Thread.interrupted()){
				if (LoginUtil.hasLogin(channel)){
					System.out.println("输入消息发送至服务端: ");
					Scanner sc = new Scanner(System.in);
					String line = sc.nextLine();

					MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
					messageRequestPacket.setMessage(line);
					ByteBuf buf = PacketCodeC.encode(channel.alloc(),messageRequestPacket);
					channel.writeAndFlush(buf);
				}
			}
		}).start();
	}
}