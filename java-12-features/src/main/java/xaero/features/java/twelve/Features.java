package xaero.features.java.twelve;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * briefly view of some java 11 features
 */
public class Features {

    public static void main(String[] args) throws IOException {
        var features = new Features();

        features.indent();
        features.mismatch();
        features.teeing();
        features.numberFormatting();
    }

    private void indent() {
        System.out.println("====== invoke indent method ======");
        var string = "simple string\nanother row";
        System.out.println(string.indent(3));
    }

    private void mismatch() throws IOException {
        System.out.println("====== invoke mismatch method ======");
        var file1 = Paths.get("java-12-features/src/main/resources/file1.txt");
        var file2 = Paths.get("java-12-features/src/main/resources/file2.txt");
        var file3 = Paths.get("java-12-features/src/main/resources/file3.txt");

        System.out.print("mismatch file1 and file2: ");
        System.out.println(Files.mismatch(file1, file2));

        System.out.print("mismatch file1 and file3: ");
        System.out.println(Files.mismatch(file1, file3));
    }

    private void teeing() {
        System.out.println("====== invoke teeing method ======");
        var numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 999);
        var minMax = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.minBy(Integer::compareTo),
                        Collectors.maxBy(Integer::compareTo),
                        (min, max) -> String.format("%s - %s", min.orElseThrow(), max.orElseThrow())
                ));
        System.out.println(minMax);
    }

    private void numberFormatting() {
        System.out.println("====== invoke number formatting method ======");
        var format = NumberFormat.getIntegerInstance(new Locale("en", "US"));
        System.out.println(format.format(1_234_567));
    }
}
