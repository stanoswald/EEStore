package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.User;
import cn.stanoswald.eestore.mapper.UserMapper;
import cn.stanoswald.eestore.service.UserService;
import cn.stanoswald.eestore.util.ImgUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private JwtEncoder encoder;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username));
    }

    @Override
    public Boolean removeByUsername(String username) {
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username));
        return userMapper.deleteById(user) == 1;
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

    @Override
    public Jwt token(User user) {
        Instant now = Instant.now();
        long expiry = 36000L;

        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(user.getUid())
                .claim("scope", scope)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims));
    }

    @Override
    public URL updateAvatar(String uid, MultipartFile file) throws IOException {
        URL url = ImgUtil.saveUserImage(uid, file);
        if (StringUtils.isNotEmpty(url.toString())) {
            User user = new User();
            user.setUid(uid);
            user.setAvatar(url.toString());
            userMapper.updateById(user);
        }
        return url;
    }

    @Override
    public List<User> adminGetAll() {
        try {
            return userMapper.selectList(new QueryWrapper<>());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Boolean updatePassword(User user, String oldPwd, String newPwd) {
        try {
            user = userMapper.selectById(user);
            if (passwordEncoder.matches(oldPwd,user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPwd));
                return userMapper.updateById(user) == 1;
            } else throw new RuntimeException("原密码不正确");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
