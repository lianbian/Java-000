package net.lianbian.rpcfx.proxy;

import java.util.concurrent.ConcurrentHashMap;

public class RpcProxy {
    private ConcurrentHashMap<String, Object> proxyCache = new ConcurrentHashMap<>();

    Object getProxy(String className) {
        return proxyCache.get(className);
    }

    Boolean isExit(String className) {
        return proxyCache.containsKey(className);
    }

    void add(String className, Object proxy) {
        proxyCache.put(className, proxy);
    }
}
