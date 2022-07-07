package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.User;
import cn.stanoswald.eestore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
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
            log.error(e.getMessage());
            return new CommonResponse.Builder().message("用户信息查询失败").error().build();
        }
    }

    @PostMapping("update")
    public ResponseEntity<Object> update(@AuthenticationPrincipal Jwt jwt, @RequestBody User user) {
        try {
            user.setUid(jwt.getSubject());
            user.setAvatar(null);

            userService.updateById(user);
            return new CommonResponse.Builder().message("用户信息更新成功").ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResponse.Builder().message("用户信息更新失败").error().build();
        }
    }

    @PostMapping("upload/img")
    public ResponseEntity<Object> updateAvatar(@AuthenticationPrincipal Jwt jwt,
                                               @RequestParam("img") MultipartFile file) {
        try {
            String imgUrl = userService.updateAvatar(jwt.getSubject(), file).toString();
            return new CommonResponse.Builder().ok().message("用户图片更新成功").data("url", imgUrl).build();
        } catch (IOException e) {
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("用户图片更新失败").build();
        }
    }

    @PostMapping("update/password")
    public ResponseEntity<Object> updatePassword(@AuthenticationPrincipal Jwt jwt
            , @RequestParam("old_password") String oldPassword
            , @RequestParam("new_password") String newPassword) {
        try {
            User user = new User();
            user.setUid(jwt.getSubject());

            return userService.updatePassword(user, oldPassword, newPassword) ?
                    new CommonResponse.Builder().message("用户密码更改成功").ok().build() :
                    new CommonResponse.Builder().message("用户密码更改失败").error().build();
        } catch (Exception e) {
            return new CommonResponse.Builder().message(e.getMessage()).error().build();
        }
    }
}
