package xaero.kafka.spring.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

/**
 * created by Nikita_Ermakov at 6/27/2022
 */
@Configuration
public class KafkaConfig {

    private final short replicationFactor = 1;
    private final int partitions = 3;

    private String kafkaServer = "localhost:9092";
    private final String topic = "spring_boot_topic";

    /**
     * Example how to create new topics from spring boot
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        final Map<String, Object> config = new HashMap<>();
        config.put(BOOTSTRAP_SERVERS_CONFIG, kafkaServer);

        return new KafkaAdmin(config);
    }

    @Bean
    public NewTopic newTopic() {
        return new NewTopic(topic, partitions, replicationFactor);
    }
}
