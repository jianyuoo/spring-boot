package air.admin.spring_boot.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径
                .allowedOrigins("*") // 允许来自指定源的请求
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // 允许的方法
//                .allowCredentials(true); // 允许携带凭证
    }
}

