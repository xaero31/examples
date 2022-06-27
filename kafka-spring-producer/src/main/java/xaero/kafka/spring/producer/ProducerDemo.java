package xaero.kafka.spring.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * created by Nikita_Ermakov at 6/27/2022
 */
@Slf4j
@Component
public class ProducerDemo implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("start kafka spring producer application");
        kafkaTemplate.send("spring_boot_topic", "message from spring kafka application");
        log.info("finish kafka spring producer application");
    }
}
