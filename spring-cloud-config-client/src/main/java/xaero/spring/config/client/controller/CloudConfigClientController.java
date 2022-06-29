package xaero.spring.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Nikita_Ermakov at 6/29/2022
 */
@RestController
@RequestMapping("/")
public class CloudConfigClientController {

    @Value("${test.value}")
    private String testValue;

    @GetMapping
    public String getTestValue() {
        return testValue;
    }
}
