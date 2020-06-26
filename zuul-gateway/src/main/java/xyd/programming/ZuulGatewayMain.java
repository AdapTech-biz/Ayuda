package xyd.programming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulGatewayMain {
    public static void main(String[] args) {
        
        try {
            Thread.sleep(15 * 1000);
            SpringApplication.run(ZuulGatewayMain.class, args);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
