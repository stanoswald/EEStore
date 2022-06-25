package cn.stanoswald.eestore.controller.admin;

import cn.stanoswald.eestore.controller.AdminController;
import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.User;
import cn.stanoswald.eestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user/")
public class AdminUserController extends AdminController {

    @Resource
    UserService userService;

    @PostMapping("set/availability")
    public ResponseEntity<Object> setAvailability(@RequestParam String uid, @RequestParam Boolean enable) {
        User user = new User();
        user.setUid(uid);
        user.setEnable(enable);
        return userService.updateById(user) ?
                new CommonResponse.Builder().ok().message("用户可用性更新成功").build() :
                new CommonResponse.Builder().ok().message("用户可用性更新失败").build();
    }


}
