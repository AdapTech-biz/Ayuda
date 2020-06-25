package xyd.programming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        // SpringApplication.run(AccountServiceApplication.class, args);

        try {
            Thread.sleep(25 * 1000);
            SpringApplication.run(AccountServiceApplication.class, args);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
