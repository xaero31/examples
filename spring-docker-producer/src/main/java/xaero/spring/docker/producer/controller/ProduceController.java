package xaero.spring.docker.producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ProduceController {

    @GetMapping("/read")
    public ResponseEntity<String> read() {
        return ok("read from docker remote producer");
    }
}
