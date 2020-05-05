package xyd.programming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChoreServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChoreServiceApplication.class, args);
    }
}
