package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.oauth2.jwt.Jwt;

public interface UserService extends IService<User> {
    User findByUsername(String username);

    Boolean removeByUsername(String username);

    String register(User user);

    Jwt token(User user);
}
