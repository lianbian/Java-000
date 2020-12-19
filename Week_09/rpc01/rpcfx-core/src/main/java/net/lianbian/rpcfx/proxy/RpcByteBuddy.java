package net.lianbian.rpcfx.proxy;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationTargetException;

public class RpcByteBuddy extends RpcProxy implements RpcClient{

    @Override
    public <T> T create(Class<T> serviceClass, String url) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(!isExit(serviceClass.getName())) {
            add(serviceClass.getName(), newProxy(serviceClass, url));
        }

        return (T) getProxy(serviceClass.getName());
    }

    @SneakyThrows
    private <T> Object newProxy(Class<T> serviceClass, String url) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass, url)))
                .make()
                .load(RpcByteBuddy.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }


}
