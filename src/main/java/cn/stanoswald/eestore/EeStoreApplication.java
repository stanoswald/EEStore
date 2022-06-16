package cn.stanoswald.eestore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
@MapperScan("cn.stanoswald.eestore.mapper")
public class EeStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(EeStoreApplication.class, args);
    }

    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .propertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
    }

}
