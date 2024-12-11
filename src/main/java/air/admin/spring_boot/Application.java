package air.admin.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static final String COMPONENT_SCAN = "air.admin";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
