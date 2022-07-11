package xaero.spring.resilience4j.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("hystrix-service/hystrix")
public interface ResilienceServiceClient {

    @GetMapping("/timeout")
    String getWithTimeout(@RequestParam(required = false) Long id);

    @GetMapping("/error")
    String getWithError(@RequestParam(required = false) Long id);

    @GetMapping("/success")
    String getSuccess(@RequestParam(required = false) Long id);
}
