package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.User;
import cn.stanoswald.eestore.mapper.UserMapper;
import cn.stanoswald.eestore.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Boolean removeByUsername(String username) {
        return userMapper.deleteByUsername(username) == 1;
    }

    @Override
    public String register(User user) {
        user.setUid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userMapper.insert(user) == 1)
            return user.getUid();
        else
            return null;
    }

}
