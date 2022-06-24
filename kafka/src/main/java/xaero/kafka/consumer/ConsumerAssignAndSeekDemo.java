package xaero.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import static java.time.Duration.ofSeconds;
import static java.util.Collections.singleton;
import static xaero.kafka.config.KafkaPropertiesUtil.getConsumerProperties;

/**
 * created by Nikita_Ermakov at 6/24/2022
 */
@Slf4j
public class ConsumerAssignAndSeekDemo {

    private static final String FIRST_TOPIC = "first_topic";

    public static void main(String[] args) {
        try (final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProperties())) {
            final TopicPartition partition = new TopicPartition(FIRST_TOPIC, 0);
            consumer.assign(singleton(partition)); // specify the reading partition
            consumer.seek(partition, 2); // specify offset for reading
            consumer.poll(ofSeconds(10)).forEach(record -> log.info("read message '{}'\npartition: '{}', offset: '{}'",
                    record.value(), record.partition(), record.offset()));
        }
    }
}
