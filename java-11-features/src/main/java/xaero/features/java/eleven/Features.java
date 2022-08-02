package xaero.features.java.eleven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static java.nio.file.StandardOpenOption.CREATE;

/**
 * briefly view of some java 11 features
 */
public class Features {

    public static void main(String[] args) throws IOException {
        var features = new Features();

        features.lines();
        features.isBlank();
        features.strip();
        features.repeat();
        features.writeToFile();
        features.readFile();
        features.toArray();
        features.predicateNot();
    }

    private void lines() {
        System.out.println("====== lines method ======");
        var multilineString = "first line\nsecond line\nthird line";
        multilineString.lines()
                .map(value -> "line:" + value)
                .forEach(System.out::println);
    }

    private void isBlank() {
        System.out.println("====== isBlank method ======");
        var spacesString = "    ";
        System.out.println(spacesString.isBlank());
    }

    /**
     * strip like trim but respects unicode spaces
     */
    private void strip() {
        System.out.println("====== strip method ======");
        var value = "   line   ";
        System.out.printf("stripped: %s%n", value.strip());
        System.out.printf("left stripped: %s%n", value.stripLeading());
        System.out.printf("right stripped: %s%n", value.stripTrailing());
    }

    private void repeat() {
        System.out.println("====== repeat method ======");
        var value = "value ";
        System.out.printf("repeated 4 times: %s", value.repeat(4));
    }

    private void writeToFile() throws IOException {
        System.out.println("====== writeToFile method ======");
        Files.writeString(Paths.get("./java-11-features/build/writeToFile.txt"), "some text\n", CREATE);
    }

    private void readFile() throws IOException {
        System.out.println("====== readFile method ======");
        System.out.println(Files.readString(Paths.get("./settings.gradle")));
    }

    private void toArray() {
        System.out.println("====== toArray method ======");
        var stringList = Arrays.asList("one", "two", "three");
        String[] array = stringList.toArray(String[]::new);
        System.out.println(Arrays.toString(array));
    }

    private void predicateNot() {
        System.out.println("====== predicateNot method ======");
        var stringList = Arrays.asList("one", "two", " ", "three");
        stringList.stream()
                .filter(Predicate.not(String::isBlank))
                .forEach(System.out::println);
    }

    private void varsInLambda() {
        System.out.println("====== varsInLambda method ======");
        var value = Optional.of("value")
                .map((@Deprecated var string) -> string.trim());
    }


}
