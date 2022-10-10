package xaero.spring.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xaero.spring.validation.service.ValidatedService;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ValidatedService validatedService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        validatedService.printMessage("first message");
        validatedService.printMessage("");
        validatedService.printMessage(null);
        validatedService.printMessage("aw2987342%2$#");
    }
}
