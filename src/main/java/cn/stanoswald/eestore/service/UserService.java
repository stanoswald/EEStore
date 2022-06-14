package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.User;

public interface UserService {
    User findByUsername(String username);

    Boolean removeByUsername(String username);

    String register(User user);
}
