package cn.stanoswald.eestore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@MapperScan("cn.stanoswald.eestore.mapper")
public class EeStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(EeStoreApplication.class, args);
    }

}
