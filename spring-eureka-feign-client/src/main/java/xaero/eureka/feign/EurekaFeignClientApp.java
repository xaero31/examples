package xaero.eureka.feign;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static org.springframework.boot.SpringApplication.run;

/**
 * created by Nikita_Ermakov at 7/1/2022
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaFeignClientApp {

    public static void main(String[] args) {
        run(EurekaFeignClientApp.class, args);
    }
}
