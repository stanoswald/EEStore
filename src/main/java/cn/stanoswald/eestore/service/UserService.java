package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface UserService extends IService<User> {
    User findByUsername(String username);

    /**
     * 根据用户名删除用户
     *
     * @param username 用户名
     * @return 是否删除成功
     */
    Boolean removeByUsername(String username);

    /**
     * 用户注册
     *
     * @param user 用户实体
     * @return 用户id
     */
    String register(User user);

    /**
     * 根据用户获取token
     *
     * @param user 用户实体
     * @return token对象
     */
    Jwt token(User user);

    /**
     * 用户头像更新
     *
     * @param uid  用户id
     * @param file 图片文件
     * @return 图片地址
     * @throws IOException 图片保存失败
     */
    URL updateAvatar(String uid, MultipartFile file) throws IOException;

    List<User> adminGetAll() throws Exception;

    Boolean updatePassword(User user,String oldPwd,String newPwd) throws Exception;
}
