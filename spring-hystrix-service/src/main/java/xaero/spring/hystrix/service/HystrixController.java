package xaero.spring.hystrix.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.sleep;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @GetMapping("/timeout")
    public ResponseEntity<String> getWithTimeout(@RequestParam(required = false) Long id) throws InterruptedException {
        log.info("get with timeout invoked: {}", id);
        sleep(30000);
        log.info("get with timeout processed: {}", id);
        return ok("success");
    }

    @GetMapping("/error")
    public ResponseEntity<String> getWithError(@RequestParam(required = false) Long id) {
        log.info("get with error invoked: {}", id);
        return status(500).body("result");
    }

    @GetMapping("/success")
    public ResponseEntity<String> getSuccess(@RequestParam(required = false) Long id) {
        log.info("get success invoked: {}", id);
        return ok("success");
    }
}
