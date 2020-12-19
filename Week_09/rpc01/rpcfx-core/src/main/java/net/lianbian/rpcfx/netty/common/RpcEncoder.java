package net.lianbian.rpcfx.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol msg, ByteBuf out) throws Exception{
        System.out.println("Netty rpc encode run");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
