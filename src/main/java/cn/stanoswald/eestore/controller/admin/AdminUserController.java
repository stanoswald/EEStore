package cn.stanoswald.eestore.controller.admin;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.User;
import cn.stanoswald.eestore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/admin/api/user/")
public class AdminUserController {

    @Resource
    UserService userService;

    @PostMapping("set/availability")
    public ResponseEntity<Object> setAvailability(@RequestParam String uid, @RequestParam Boolean enable) {
        User user = new User();
        user.setUid(uid);
        user.setEnable(enable);
        return userService.updateById(user) ?
                new CommonResponse.Builder().ok().message("用户可用性更新成功").build() :
                new CommonResponse.Builder().error().message("用户可用性更新失败").build();
    }

    @GetMapping("get/all")
    public ResponseEntity<Object> getAll() {
        try {
            return new CommonResponse.Builder().ok().message("管理员获取所有用户成功")
                    .data("user_list", userService.adminGetAll()).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("管理员获取所有用户失败").build();
        }
    }

}
