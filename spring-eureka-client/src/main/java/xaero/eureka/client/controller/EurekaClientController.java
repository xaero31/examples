package xaero.eureka.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Nikita_Ermakov at 6/30/2022
 */
@Slf4j
@RestController
@RequestMapping("/eureka")
public class EurekaClientController {

    private final String testValue = "eureka client test value";

    @GetMapping
    public String getTestValue() {
        log.info("invoked getTestValue");
        return testValue;
    }
}
