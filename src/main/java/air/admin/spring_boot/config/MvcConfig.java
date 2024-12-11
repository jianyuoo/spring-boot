package air.admin.spring_boot.config;

import air.admin.spring_boot.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class MvcConfig {

    @Autowired
    private Interceptor interceptor;


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.interceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login/**");
    }
}
