package im.netty;

import im.handle.*;
import im.server.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import netty.FirstServerHandler;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/16
 * @Time: 下午10:56
 * @Description:
 */
public class NettyServer {
	public static void main(String[] args) {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
//						nioSocketChannel.pipeline().addLast(new LifeCycleTestHandler());
						nioSocketChannel.pipeline().addLast(new Spliter());
						nioSocketChannel.pipeline().addLast(new PacketDecoder());
						nioSocketChannel.pipeline().addLast(new LoginRequestHandler());
						nioSocketChannel.pipeline().addLast(new AuthHandler());
						nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
						nioSocketChannel.pipeline().addLast(new CreateGroupRequestHandler());
						nioSocketChannel.pipeline().addLast(new JoinGroupRequestHandler());
						nioSocketChannel.pipeline().addLast(new PacketEncoder());
					}
				});
		serverBootstrap.bind(8080).addListener(new GenericFutureListener<Future<? super Void>>() {
			@Override
			public void operationComplete(Future<? super Void> future) throws Exception {
				if (future.isSuccess()) {
					System.out.println("端口绑定成功!");
				} else {
					System.err.println("端口绑定失败!");
					bind(serverBootstrap, 8081);
				}

			}
		});
	}

	private static void bind(final ServerBootstrap serverBootstrap, final int port) {
		serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
			@Override
			public void operationComplete(Future<? super Void> future) {
				if (future.isSuccess()) {
					System.out.println("端口[" + port + "]绑定成功!");
				} else {
					System.err.println("端口[" + port + "]绑定失败!");
					bind(serverBootstrap, port + 1);
				}
			}
		});
	}
}