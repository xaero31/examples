package xaero.spring.hystrix.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xaero.spring.hystrix.client.service.HystrixService;

@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    private final HystrixService service;

    public HystrixController(HystrixService service) {
        this.service = service;
    }

    @GetMapping("/timeout")
    public String getWithTimeout() {
        return service.getWithTimeout();
    }

    @GetMapping("/error")
    public String getWithError() {
        return service.getWithError();
    }

    @GetMapping("/success")
    public String getSuccess() {
        return service.getSuccess();
    }
}
