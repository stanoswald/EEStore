package cn.stanoswald.eestore;

import cn.stanoswald.eestore.entity.User;
import cn.stanoswald.eestore.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EeStoreApplicationTests {

    static final String TEST_USERNAME = "";
    static final String ADMIN_USERNAME = "";
    static User testAccount, adminAccount;

    static {
        testAccount = new User();
        testAccount.setUsername(TEST_USERNAME);
        testAccount.setPassword("");
        testAccount.setTel("15980123456");
        testAccount.setEmail("test@testmail.com");
        testAccount.setAvatar("http://test");
        testAccount.setRole("USER");
        testAccount.setEnable(true);

        adminAccount = new User();
        adminAccount.setUsername(ADMIN_USERNAME);
        adminAccount.setPassword("");
        adminAccount.setTel("15980000000");
        adminAccount.setEmail("admin@adminmail.com");
        adminAccount.setAvatar("http://test/admin");
        adminAccount.setRole("ADMIN");
        adminAccount.setEnable(true);
    }

    @Autowired
    UserService userService;

    @Test
    void accountTest() {
        register();
        // select();
        // delete();
    }

    void register() {
        String uid = userService.register(adminAccount);
        System.out.println(uid);
    }

    void select() {
        User user = userService.findByUsername(TEST_USERNAME);
        assert user.equals(testAccount);
    }

    void delete() {
        assert userService.removeByUsername(TEST_USERNAME);
    }


}
