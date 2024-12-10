package air.admin.spring_boot;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


//项目启动类
@EnableCaching  //启用Spring的缓存支持
@EnableScheduling  //启用Spring的定时任务执行
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)  //启用AspectJ自动代理支持，允许使用AOP（面向切面编程）功能
@ComponentScan(Application.COMPONENT_SCAN)  //指定要扫描的组件包，以便Spring能够识别和注册这些组件
@MapperScan(value = Application.COMPONENT_SCAN, annotationClass = Mapper.class)  //用于扫描MyBatis的Mapper接口，将其注册到Spring上下文中，以便自动创建实现。
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})  //用于标记主类，表示这是一个Spring Boot应用
public class Application {

    public static final String COMPONENT_SCAN = "air.admin";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
