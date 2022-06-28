package xaero.kafka.streams.config;

import org.apache.kafka.common.serialization.Serdes;

import java.util.Properties;

import static org.apache.kafka.streams.StreamsConfig.*;

/**
 * created by Nikita_Ermakov at 6/28/2022
 */
public class KafkaConnectionConfigUtil {

    private static final String KAFKA_SERVER = "localhost:9092";
    private static final String STREAMS_APP_ID = "streams_app";

    public static Properties getKafkaStreamsProperties() {
        final Properties properties = new Properties();

        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        properties.setProperty(APPLICATION_ID_CONFIG, STREAMS_APP_ID);
        properties.setProperty(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        properties.setProperty(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());

        return properties;
    }
}
