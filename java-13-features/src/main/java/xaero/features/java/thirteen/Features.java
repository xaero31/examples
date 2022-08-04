package xaero.features.java.thirteen;

/**
 * briefly view of some java 13 features
 */
public class Features {

    public static void main(String[] args) {
        var features = new Features();

        features.switchExpression();
        features.textBlock();
    }

    private void switchExpression() {
        System.out.println("====== invoke switch expression method ======");
        var stub = "stub";
        var text = switch (stub) {
            case "not stub" -> "not stub";
            case "stub" -> {
                var anotherString = "value for bigger case expression";
                yield "this is stub";
            }
            default -> "default";
        };
        System.out.println(text);
    }

    private void textBlock() {
        System.out.println("====== invoke text block method ======");
        var block = """
                {
                    "someString": "someValue and \\n%s"
                }""";
        System.out.println(block);

        System.out.println("formatted:");
        System.out.println(block.formatted("formatted value"));

        System.out.println("escaped:");
        System.out.println(block.translateEscapes()); // respects escaped characters

        var notBlock = "  {\n" +
                "      \"someString\": \"someValue and %s\"\n" +
                "  }";
        System.out.println("stripped:");
        System.out.println(notBlock.stripIndent()); // removes white spaces from start of string/block
    }
}
