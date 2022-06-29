package xaero.eureka.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import static org.springframework.boot.SpringApplication.run;

/**
 * created by Nikita_Ermakov at 6/29/2022
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {

    public static void main(String[] args) {
        run(EurekaServerApp.class, args);
    }
}
