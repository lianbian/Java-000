package net.lianbian.rpcfx.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import lombok.extern.slf4j.Slf4j;
import net.lianbian.rpcfx.api.RpcfxRequest;
import net.lianbian.rpcfx.api.RpcfxResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

@Slf4j
public class RpcInvocationHandler implements InvocationHandler {
    private final Class<?> serviceClass;
    private final String url;

    public <T> RpcInvocationHandler(Class<T> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        return process(serviceClass, method, args, url);
    }

    /**
     * 发送请求到服务端
     * 获取结果后序列号成对象，返回
     * @param service service name
     * @param method service method
     * @param params method params
     * @param url server host
     * @return object
     */
    private Object process(Class<?> service, Method method, Object[] params, String url) {
        System.out.println("Client proxy instance method invoke");

        // 自定义了Rpc请求的结构 RpcRequest,放入接口名称、方法名、参数
        System.out.println("Build Rpc request");
        RpcfxRequest rpcRequest = new RpcfxRequest();
        rpcRequest.setServiceClass(service.getName());
        rpcRequest.setMethod(method.getName());
        rpcRequest.setParams(params);

        // 客户端使用的 netty，发送请求到服务端，拿到结果（自定义结构：rpcfxResponse)
        System.out.println("Client send request to Server");
        RpcfxResponse rpcResponse;
        try {
            rpcResponse = RpcNettyClientSync.getInstance().getResponse(rpcRequest, url);
        } catch (InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Client receive response Object");
        assert rpcResponse != null;
        if (!rpcResponse.getStatus()) {
            System.out.println("Client receive exception");
            rpcResponse.getException().printStackTrace();
            return null;
        }

        // 序列化成对象返回
        System.out.println("Response:: " + rpcResponse.getResult());
        ParserConfig.getGlobalInstance().addAccept("net.lianbian.rpcfx.demo.api.");
        return JSON.parse(rpcResponse.getResult().toString());
    }
}
