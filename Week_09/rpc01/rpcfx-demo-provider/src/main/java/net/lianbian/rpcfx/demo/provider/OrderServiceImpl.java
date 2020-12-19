package net.lianbian.rpcfx.demo.provider;

import net.lianbian.rpcfx.demo.api.Order;
import net.lianbian.rpcfx.demo.api.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f, 110);
    }
}
