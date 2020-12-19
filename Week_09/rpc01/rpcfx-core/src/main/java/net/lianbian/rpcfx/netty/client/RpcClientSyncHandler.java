package net.lianbian.rpcfx.netty.client;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import net.lianbian.rpcfx.api.RpcfxRequest;
import net.lianbian.rpcfx.api.RpcfxResponse;
import net.lianbian.rpcfx.netty.common.RpcProtocol;

import java.util.concurrent.CountDownLatch;

public class RpcClientSyncHandler extends SimpleChannelInboundHandler<RpcProtocol> {

    private CountDownLatch latch;
    private RpcfxResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol msg) {
        System.out.println("Netty client receive message:");
        System.out.println("Message length: " + msg.getLen());
        System.out.println("Message content: " + new String(msg.getContent(), CharsetUtil.UTF_8));

        // 将 RpcResponse字符串 反序列化成 RpcResponse对象
        RpcfxResponse rpcfxResponse = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8),
                RpcfxResponse.class);
        System.out.println("Netty client serializer : " + rpcfxResponse.toString());

        response = rpcfxResponse;
        latch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 锁的初始化
     * @param latch CountDownLatch
     */
    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 阻塞等待结果后返回
     * @return 后台服务器响应
     * @throws InterruptedException exception
     */
    public RpcfxResponse getResponse() throws InterruptedException {
        latch.await();
        return response;
    }
}
