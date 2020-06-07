package im.handle;

import im.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: xiexipeng
 * @Date: 2020/6/3
 * @Time: 上午12:05
 * @Description:
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) {
        String fromUserName = msg.getFromUserName();
        System.out.println("收到来自" + fromUserName + "的消息: " + " -> " + msg.getMessage());
    }
}