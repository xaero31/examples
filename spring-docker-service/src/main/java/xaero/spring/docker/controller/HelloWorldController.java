package xaero.spring.docker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Paths;

import static java.lang.System.getenv;
import static java.nio.file.Files.readString;
import static java.nio.file.Files.writeString;
import static java.nio.file.StandardOpenOption.CREATE;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloWorldController {

    private final RestTemplate restTemplate;

    @GetMapping("/helloWorld")
    public ResponseEntity<String> helloWorld() {
        return ok("new version of hello world");
    }

    @GetMapping("/environment")
    public ResponseEntity<String> environment() {
        return ok(getenv("test.property"));
    }

    @GetMapping("/write")
    public ResponseEntity<String> write() throws IOException {
        writeString(Paths.get("/user-files/test.txt"), "text from application", CREATE);
        return ok("file is written");
    }

    @GetMapping("/read")
    public ResponseEntity<String> read() throws IOException {
        return ok(readString(Paths.get("/user-files/test.txt")));
    }

    @GetMapping("/readRemote")
    public ResponseEntity<String> readRemote() {
        return ok(restTemplate.getForObject("http://producer:8080/read", String.class));
    }
}
