package xaero.eureka.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.springframework.boot.SpringApplication.run;

/**
 * created by Nikita_Ermakov at 6/30/2022
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientApp {

    public static void main(String[] args) {
        run(EurekaClientApp.class, args);
    }
}
