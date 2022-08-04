package xaero.features.java.sixteen;

import xaero.features.java.sixteen.interfaces.DefaultInterface;
import xaero.features.java.sixteen.record.RecordClass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.ClassLoader.getSystemClassLoader;

/**
 * briefly view of some java 11 features
 */
public class Features {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        var features = new Features();

        features.invokeProxyDefaultMethod();
        features.dayPeriodSupport();
        features.streamToList();
        features.recordExample();
        features.patternMatching();
    }

    private void invokeProxyDefaultMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("====== invoke proxy interface default method ======");
        var proxy = Proxy.newProxyInstance(getSystemClassLoader(), new Class<?>[]{DefaultInterface.class},
                (prox, method, args) -> {
                    if (method.isDefault()) {
                        return InvocationHandler.invokeDefault(prox, method, args);
                    } else {
                        return null;
                    }
                }
        );
        var method = proxy.getClass().getMethod("hi");
        System.out.println(method.invoke(proxy));
    }

    private void dayPeriodSupport() {
        System.out.println("====== invoke day period support method ======");
        var date = LocalTime.parse("15:25:08.690791");
        var formatter = DateTimeFormatter.ofPattern("h B");
        System.out.println(date.format(formatter));
    }

    private void streamToList() {
        System.out.println("====== invoke stream to list method ======");
        System.out.println(Stream.of("one", "two", "three").toList());
    }

    private void recordExample() {
        System.out.println("====== invoke record example method ======");
        var record = new RecordClass(1337L, "simple record", "autistic author");
        System.out.println(record);
    }

    private void patternMatching() {
        System.out.println("====== invoke pattern matching method ======");
        final List<String> list = new LinkedList<>(Arrays.asList("one", "two", "three"));
        if (list instanceof LinkedList<String> linkedList) {
            System.out.println(linkedList.peek());
        }
    }
}
