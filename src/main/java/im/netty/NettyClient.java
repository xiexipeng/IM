package im.netty;

import im.LoginRequestPacket;
import im.PacketCodeC;
import im.client.ClientHandler;
import im.command.ConsoleCommad;
import im.command.ConsoleCommadManager;
import im.command.LoginCommand;
import im.common.LoginUtil;
import im.handle.*;
import im.packet.MessageRequestPacket;
import im.session.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
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
						// inBound，处理读数据的逻辑链
						ch.pipeline().addLast(new Spliter());
						ch.pipeline().addLast(new PacketDecoder());
						ch.pipeline().addLast(new LoginResponseHander());
						ch.pipeline().addLast(new MessageResponseHandler());
						ch.pipeline().addLast(new CreateGroupResponseHandler());
						ch.pipeline().addLast(new JoinGroupResponseHandler());
						ch.pipeline().addLast(new PacketEncoder());

					}
				});
		// 4.建立连接
		connect(bootstrap, "localhost", 8080, MAX_RETRY);
	}

	/**
	 * @param bootstrap
	 * @param host
	 * @param port
	 * @param retry:剩余重试次数
	 */
	private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
		bootstrap.connect(host, port).addListener(future -> {
			if (future.isSuccess()) {
				System.out.println("连接成功!");
				Channel channel = ((ChannelFuture) future).channel();
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

	private static void startConsoleThread(Channel channel) {
		ConsoleCommadManager consoleCommadManager = new ConsoleCommadManager();
		LoginCommand loginCommand = new LoginCommand();
		Scanner sc = new Scanner(System.in);
		new Thread(() -> {
			while (!Thread.interrupted()) {
				if (!SessionUtil.hasLogin(channel)) {
					loginCommand.exec(sc, channel);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					consoleCommadManager.exec(sc, channel);
				}
			}
		}).start();
	}
}