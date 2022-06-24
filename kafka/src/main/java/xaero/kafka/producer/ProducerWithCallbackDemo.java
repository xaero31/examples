package xaero.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static xaero.kafka.config.KafkaPropertiesUtil.getProducerProperties;

/**
 * created by Nikita_Ermakov at 6/24/2022
 * <p>
 * simple kafka producer example
 * <p>
 * https://kafka.apache.org/20/documentation.html#producerconfigs
 */
@Slf4j
public class ProducerWithCallbackDemo {

    private static final String FIRST_TOPIC = "first_topic";

    public static void main(String[] args) {
        try (final KafkaProducer<String, String> producer = new KafkaProducer<>(getProducerProperties())) {
            final ProducerRecord<String, String> record = new ProducerRecord<>(FIRST_TOPIC, "message from java");
            // send is async, so we could flush or close producer then
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    log.info("message '{}' was successfully sent", record.value());
                } else {
                    log.error("got exception when sending '{}' message", record.value(), exception);
                }
            });
        }
    }
}
