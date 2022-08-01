package xaero.features.java.nine;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * briefly view of some java 9 features
 */
public class Features {

    /**
     * gets current jvm process and information about it
     */
    private void processFeature() {
        final ProcessHandle currentJvmProcess = ProcessHandle.current();
        final ProcessHandle.Info info = currentJvmProcess.info();

        final Optional<String[]> processArguments = info.arguments();
        final Optional<String> commandLine = info.commandLine();
        final Optional<Instant> startTime = info.startInstant();
        final Optional<Duration> cpuUsage = info.totalCpuDuration();
    }

    private void httpExample() throws URISyntaxException, IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://google.com"))
                .GET()
                .build();

        final HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void immutableSet() {
        final Set<String> immutableSet = Set.of("kek", "cheburek", "1337");
    }

    private void convertOptionalToStream() {
        final Optional<String> optionalString = Optional.of("optional string");
        final Stream<String> streamOfOptionalString = optionalString.stream();
        final List<String> listOfValuesOfOptionalString = streamOfOptionalString.collect(toList());
    }
}
