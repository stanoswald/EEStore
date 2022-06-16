package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.User;
import cn.stanoswald.eestore.mapper.UserMapper;
import cn.stanoswald.eestore.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JwtEncoder encoder;

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
}
