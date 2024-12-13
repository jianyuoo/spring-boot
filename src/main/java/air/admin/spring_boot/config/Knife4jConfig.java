package air.admin.spring_boot.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        //创建一个 OpenAPI 对象，用于表示整个 API 的文档信息
        return new OpenAPI()
                 //接口文档标题
                 .info(new Info().title("API接口文档")
                 //接口文档简介  description
                .description("我的第一个Knife4j")
                 //接口文档版本
                .version("0.0.1-SNAPSHOT")
                 //开发者的联系方式，包括姓名和电子邮件地址
                .contact(new Contact().name("").email("")));
    }
}

