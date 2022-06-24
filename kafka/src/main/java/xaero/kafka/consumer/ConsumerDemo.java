package xaero.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static java.time.Duration.ofSeconds;
import static java.util.Collections.singleton;
import static xaero.kafka.config.KafkaPropertiesUtil.getConsumerProperties;

/**
 * created by Nikita_Ermakov at 6/24/2022
 */
@Slf4j
public class ConsumerDemo {

    private static final String FIRST_TOPIC = "first_topic";

    public static void main(String[] args) {
        try (final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProperties())) {
            consumer.subscribe(singleton(FIRST_TOPIC));
            consumer.poll(ofSeconds(10)).forEach(record -> log.info("read message '{}'", record.value()));
        }
    }
}
