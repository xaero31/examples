package xaero.eureka.ribbon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * created by Nikita_Ermakov at 6/30/2022
 */
@Slf4j
@RestController
@RequestMapping("/ribbon")
public class RibbonClientController {

    private final RestTemplate restTemplate;
    private final String serviceUrl = "http://eurekaservice/eureka";

    @Autowired
    public RibbonClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getTestValue() {
        log.info("invoked transitive getTestValue");
        return restTemplate.getForObject(serviceUrl, String.class);
    }
}
