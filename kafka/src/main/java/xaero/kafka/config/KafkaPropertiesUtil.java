package xaero.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

/**
 * created by Nikita_Ermakov at 6/24/2022
 */
public class KafkaPropertiesUtil {

    private static final String KAFKA_LOCAL_SERVER = "localhost:9092";
    private static final String JAVA_GROUP_ID = "java_group";
    private static final String EARLIEST_OFFSET = "earliest";

    public static Properties getProducerProperties() {
        final Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_LOCAL_SERVER);
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

    public static Properties getConsumerProperties() {
        final Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_LOCAL_SERVER);
        properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(GROUP_ID_CONFIG, JAVA_GROUP_ID);
        properties.setProperty(AUTO_OFFSET_RESET_CONFIG, EARLIEST_OFFSET);

        return properties;
    }
}
