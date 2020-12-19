package net.lianbian.rpcfx.demo.provider;

import net.lianbian.rpcfx.demo.api.User;
import net.lianbian.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
