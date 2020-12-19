package net.lianbian.rpcfx.demo.consumer;

import net.lianbian.rpcfx.demo.api.Order;
import net.lianbian.rpcfx.demo.api.OrderService;
import net.lianbian.rpcfx.demo.api.User;
import net.lianbian.rpcfx.demo.api.UserService;
import net.lianbian.rpcfx.proxy.RpcByteBuddy;
import net.lianbian.rpcfx.proxy.RpcClient;
import net.lianbian.rpcfx.proxy.RpcClientJdk;

import java.lang.reflect.InvocationTargetException;

public class ClientApplication {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        RpcClient jdk = new RpcClientJdk();
        UserService userService = jdk.create(UserService.class, "http://localhost:8081/");
        User user = userService.findById(1);
        if (user == null) {
            System.out.println("Clint service invoke Error");
            return;
        }
        System.out.println("find user id=1 from server: " + user.getName());

        RpcClient buddy = new RpcByteBuddy();
        OrderService orderService = buddy.create(OrderService.class, "http://localhost:8081/");
        Order order = orderService.findOrderById(1992129);
        if (order == null) {
            System.out.println("Clint service invoke Error");
            return;
        }
        System.out.println(String.format("find order name=%s, user=%d",order.getName(),order.getUserId()));
    }
}
