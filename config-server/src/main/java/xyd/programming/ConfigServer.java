package xyd.programming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServer {

    public static void main(String[] args) {
        try {
            Thread.sleep(3 * 1000);
            SpringApplication.run(ConfigServer.class, args);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        
    }
}
