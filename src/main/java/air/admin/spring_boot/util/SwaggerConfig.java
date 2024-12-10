package air.admin.spring_boot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Conditional(SystemEnvironmentConfig.class)
public class SwaggerConfig {
}
