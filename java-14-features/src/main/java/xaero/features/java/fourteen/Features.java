package xaero.features.java.fourteen;

/**
 * briefly view of some java 11 features
 */
public class Features {

    public static void main(String[] args) {
        var features = new Features();

        // commented because of exception
        // features.additionalStackTraceInfoOnNpe();
    }

    private void additionalStackTraceInfoOnNpe() {
        final String value = null;
        System.out.println(value.length());
    }
}
