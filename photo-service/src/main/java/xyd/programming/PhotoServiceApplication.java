package xyd.programming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import xyd.programming.entity.Photo;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class PhotoServiceApplication {

    public static void main(String[] args) {
        Log.info("running Photo Service");
        SpringApplication.run(PhotoServiceApplication.class, args);
    }
}
