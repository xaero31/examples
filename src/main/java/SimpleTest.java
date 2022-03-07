import pkg.TestObject;

public class SimpleTest {
    public static void main(String[] args) {
        try {
            new TestObject().go();
        } catch (Exception e) {
            for (StackTraceElement element : e.getStackTrace()) {
                System.out.println("classname: " + element.getClassName());
                System.out.println("methodname: " + element.getMethodName());
                System.out.println("filename: " + element.getFileName());
                System.out.println("linenumber: " + element.getLineNumber());
            }
        }
    }
}
