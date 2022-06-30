package xaero.eureka.feign.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xaero.eureka.feign.client.EurekaFeignClient;

/**
 * created by Nikita_Ermakov at 7/1/2022
 */
@Slf4j
@RestController
@RequestMapping("/feign")
public class FeignClientController {

    private final EurekaFeignClient feignClient;

    @Autowired
    public FeignClientController(EurekaFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @GetMapping
    public String getTestValue() {
        log.info("invoked transitive getTestValue");
        return feignClient.getTestValue();
    }
}
