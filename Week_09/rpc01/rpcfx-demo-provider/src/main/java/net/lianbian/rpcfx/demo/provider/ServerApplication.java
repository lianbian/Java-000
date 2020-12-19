package net.lianbian.rpcfx.demo.provider;

import net.lianbian.rpcfx.demo.api.OrderService;
import net.lianbian.rpcfx.demo.api.UserService;
import net.lianbian.rpcfx.netty.server.RpcNettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"net.lianbian.rpcfx.netty.server"})
public class ServerApplication implements ApplicationRunner {
    private final RpcNettyServer rpcNettyServer;

    public ServerApplication(RpcNettyServer rpcNettyServer) {
        this.rpcNettyServer = rpcNettyServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            rpcNettyServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rpcNettyServer.destroy();
        }
    }

    @Bean(name = "net.lianbian.rpcfx.demo.api.UserService")
    public UserService createUserService(){
        return new UserServiceImpl();
    }

    @Bean(name = "net.lianbian.rpcfx.demo.api.OrderService")
    public OrderService createOrderService(){
        return new OrderServiceImpl();
    }
}
