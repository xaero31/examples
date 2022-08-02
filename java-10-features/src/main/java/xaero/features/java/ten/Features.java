package xaero.features.java.ten;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * briefly view of some java 10 features
 */
public class Features {

    private void typeInference() {
        // lambda can not use var
        // var lambda = () -> System.out.println("this is lambda");

        var map = new HashMap<String, Object>();

        // var can not be null, could specify specific type when initialized
        // var nullValue = null;

        // var could be initialized
        // var notInitValue;
    }

    private void unmodifiableCollections() {
        final List<String> unmodifiable = List.copyOf(Arrays.asList("one", "two", "three"));
    }

    private void streamToUnmodifiableCollection() {
        final List<String> unmodifiable = Stream.of("one", "two", "three")
                .collect(Collectors.toUnmodifiableList());
    }

    private void optionalOrElseThrowWithoutArgument() {
        final String first = Stream.of("one", "two", "three")
                .findFirst()
                .orElseThrow();
    }
}
