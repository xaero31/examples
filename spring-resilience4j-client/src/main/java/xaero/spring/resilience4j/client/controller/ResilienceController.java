package xaero.spring.resilience4j.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xaero.spring.resilience4j.client.service.ResilienceService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/resilience")
public class ResilienceController {

    private final ResilienceService service;

    public ResilienceController(ResilienceService service) {
        this.service = service;
    }

    @GetMapping("/timeout")
    public String getWithTimeout() throws ExecutionException, InterruptedException {
        return service.getWithTimeout().get();
    }

    @GetMapping("/error")
    public String getWithError() {
        return service.getWithError();
    }

    @GetMapping("/error-param")
    public String getWithErrorAndParam(@RequestParam Long id) {
        return service.getWithErrorAndParam(id);
    }

    @GetMapping("/success")
    public String getSuccess() {
        return service.getSuccess();
    }
}
