package xaero.kafka.spring.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * created by Nikita_Ermakov at 6/27/2022
 */
@Slf4j
@Component
public class KafkaConsumerPojo {

    @KafkaListener(topics = "spring_boot_topic", groupId = "spring_boot_app")
    public void printKafkaMessage(String message) {
        log.info("read message from kafka: [{}]", message);
    }
}
