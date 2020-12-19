package net.lianbian.rpcfx.netty.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import net.lianbian.rpcfx.api.RpcfxRequest;
import net.lianbian.rpcfx.api.RpcfxResponse;
import net.lianbian.rpcfx.netty.common.RpcProtocol;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcProtocol> {
    private ApplicationContext applicationContext;

    RpcServerHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol rpcProtocol) throws Exception {
        System.out.println("Netty server receive message:");
        System.out.println("Message length:" + rpcProtocol.getLen());
        System.out.println("Message content:" + new String(rpcProtocol.getContent(), CharsetUtil.UTF_8));

        // 获取 RpcProtocol中的 RpcRequest内容，反序列化成 RpcRequest 对象
        RpcfxRequest rpcfxRequest = JSON.parseObject(new String(rpcProtocol.getContent(), CharsetUtil.UTF_8),
                RpcfxRequest.class);

        System.out.println("Netty server serializer : " + rpcfxRequest.toString());

        // 获取相应的bean，反射调用方法，获取结果
        RpcfxResponse response = invoke(rpcfxRequest);
        RpcProtocol message = new RpcProtocol();
        String requestJson = JSON.toJSONString(response);
        message.setLen(requestJson.getBytes(CharsetUtil.UTF_8).length);
        message.setContent(requestJson.getBytes(CharsetUtil.UTF_8));

        channelHandlerContext.writeAndFlush(message).sync();
        System.out.println("return response to client end");
    }

    private RpcfxResponse invoke(RpcfxRequest rpcfxRequest) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = rpcfxRequest.getServiceClass();

        // 作业1：改成泛型和反射
        Object service = applicationContext.getBean(serviceClass);
        try {
            Method method = resolveMethodFromClass(service.getClass(), rpcfxRequest.getMethod());
            Object result = method.invoke(service, rpcfxRequest.getParams());
            System.out.println("Server method invoke result: " + result.toString());
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            System.out.println("Server Response serialize to string return");
            return response;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}