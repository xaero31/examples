package xaero.kafka.streams;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

import static java.lang.Thread.sleep;
import static xaero.kafka.streams.config.KafkaConnectionConfigUtil.getKafkaStreamsProperties;

/**
 * created by Nikita_Ermakov at 6/28/2022
 *
 * demo filter kafka from input 'first_topic' to output 'streams_filtered_topic'
 */
public class KafkaStreamsFilterDemo {

    private static final String KEYWORD = "xaero";
    private static final String FIRST_TOPIC = "first_topic";
    private static final String FILTERED_TOPIC = "streams_filtered_topic";

    public static void main(String[] args) throws InterruptedException {
        // create builder
        final StreamsBuilder streamsBuilder = new StreamsBuilder();
        // read input queue
        final KStream<String, String> input = streamsBuilder.stream(FIRST_TOPIC);
        // filter messages
        final KStream<String, String> filtered = input.filter((key, value) -> value.contains(KEYWORD));
        // set output queue
        filtered.to(FILTERED_TOPIC);

        // create connection to kafka
        try (final KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), getKafkaStreamsProperties())) {
            // run application
            streams.start();
            sleep(10000); // because start is async and stopped when streams are closed in try-with-resources
        }
    }
}
