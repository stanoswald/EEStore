package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.User;
import cn.stanoswald.eestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/api")
public class UserController {

    @Resource
    UserService userService;


    @GetMapping("get")
    public ResponseEntity<Object> get(@AuthenticationPrincipal Jwt jwt) {
        try {
            User user = userService.getById(jwt.getSubject());
            return new CommonResponse.Builder().ok().message("用户信息查询成功")
                    .data("user", user).build();
        } catch (Exception e) {
            return new CommonResponse.Builder().message("用户信息查询失败").error().build();
        }
    }
}
