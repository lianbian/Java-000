package net.lianbian.rpcfx.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import net.lianbian.rpcfx.netty.common.RpcDecoder;
import net.lianbian.rpcfx.netty.common.RpcEncoder;

public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast("Message Encoder", new RpcEncoder());
        channelPipeline.addLast("Message Decoder", new RpcDecoder());
        channelPipeline.addLast("clientHandler", new RpcClientSyncHandler());
    }
}
