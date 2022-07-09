package xaero.spring.hystrix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringHystrixServiceApp {

    public static void main(String[] args) {
        run(SpringHystrixServiceApp.class, args);
    }
}
