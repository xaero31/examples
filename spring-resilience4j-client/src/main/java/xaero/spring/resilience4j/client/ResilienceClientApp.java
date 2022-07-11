package xaero.spring.resilience4j.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ResilienceClientApp {

    public static void main(String[] args) {
        run(ResilienceClientApp.class, args);
    }
}
