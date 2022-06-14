package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("unauthorized")
    public ResponseEntity<Object> unauthorized() {
        return new CommonResponse.Builder(HttpStatus.UNAUTHORIZED)
                .message("未登录").build();
    }

    @GetMapping("login")
    public ResponseEntity<Object> login() {
        return new CommonResponse.Builder(HttpStatus.METHOD_NOT_ALLOWED)
                .message("方法不允许").build();
    }

    @PostMapping("login/success")
    public ResponseEntity<Object> loginSuccess(@AuthenticationPrincipal final User user) {
        return new CommonResponse.Builder()
                .ok().message("登录成功")
                .data("user", user).build();
    }

    @PostMapping("login/failure")
    public ResponseEntity<Object> loginFailure(@RequestAttribute("SPRING_SECURITY_LAST_EXCEPTION") AuthenticationException exception) {

        return new CommonResponse.Builder()
                .ok().message("登录失败")
                .data("info", exception.getMessage()).build();
    }

}
