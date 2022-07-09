package xaero.spring.hystrix.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static org.springframework.boot.SpringApplication.run;

/**
 * Netflix Hystrix is dead. Should use Resilience4j instead in future.
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class HystrixClientApp {

    public static void main(String[] args) {
        run(HystrixClientApp.class, args);
    }
}
