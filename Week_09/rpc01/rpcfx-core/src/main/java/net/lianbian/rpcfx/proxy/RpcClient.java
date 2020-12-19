package net.lianbian.rpcfx.proxy;

import java.lang.reflect.InvocationTargetException;

public interface RpcClient {
    /**
     * create proxy
     * @param serviceClass service class
     * @param url server url
     * @param <T> T
     * @return proxy class
     */
    <T> T create(final Class<T> serviceClass, final String url) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
