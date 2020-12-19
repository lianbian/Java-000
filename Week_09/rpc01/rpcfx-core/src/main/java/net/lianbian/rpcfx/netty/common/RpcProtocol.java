package net.lianbian.rpcfx.netty.common;

import lombok.Data;

@Data
public class RpcProtocol {
    private int len;

    private byte[] content;

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setLen(int length) {
        this.len = length;
    }

    public int getLen() {
        return this.len;
    }

    public byte[] getContent() {
        return this.content;
    }
}
