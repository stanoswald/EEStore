package cn.stanoswald.eestore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("cn.stanoswald.eestore.mapper")
public class EeStoreApplication implements WebMvcConfigurer {

    private static final String[] RESOURCE_HANDLER_PATTERNS =
            {"/img/user/**", "/img/product/**"};
    private static final String[] RESOURCE_LOCATIONS =
            {"file:img/user/", "file:img/product/"};

    public static void main(String[] args) {
        SpringApplication.run(EeStoreApplication.class, args);
    }

    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .propertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCE_HANDLER_PATTERNS)
                .addResourceLocations(RESOURCE_LOCATIONS);
    }
}
