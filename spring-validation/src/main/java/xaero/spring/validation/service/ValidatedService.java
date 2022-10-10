package xaero.spring.validation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Slf4j
@Component
@Validated
public class ValidatedService {

    public void printMessage(@NotBlank @Pattern(regexp = "[A-Za-z\\s]*") String message) {
        log.info("message is: '{}'", message);
    }
}
