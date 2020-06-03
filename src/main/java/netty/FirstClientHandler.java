package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author: xiexipeng
 * @Date: 2020/5/30
 * @Time: 下午6:36
 * @Description:
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端成功建立连接后执行此方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端写出数据");
        for (int i = 0; i < 1; i++) {
            // 1. 获取数据
            ByteBuf buffer = getByteBuf(ctx);

            // 2. 写数据
            ctx.channel().writeAndFlush(buffer);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + ": 客户端读到数据 -> " + buf.toString(Charset.forName("utf-8")));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buf = ctx.alloc().buffer();
        // 2. 准备数据，指定字符串的字符集为 utf-8
        byte[] bytes = "Hello World!".getBytes(Charset.forName("utf-8"));
        // 3. 填充数据到 ByteBuf
        buf.writeBytes(bytes);
        return buf;
    }
}