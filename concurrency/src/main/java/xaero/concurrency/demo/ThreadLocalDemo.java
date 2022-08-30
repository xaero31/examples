package xaero.concurrency.demo;

import lombok.extern.java.Log;

import java.util.concurrent.Executors;

@Log
public class ThreadLocalDemo {

    /**
     * thread local helps to define values accessible only from current thread
     */
    public static void main(String[] args) {
        // simple creating
        final var local = new ThreadLocal<String>();
        final var executor = Executors.newSingleThreadExecutor();

        // example of setting
        local.set("hello world");
        log.info("get from thread local: " + local.get());

        // will print null because of another thread
        executor.submit(() -> log.info("get from executor: " + local.get()));

        // another way to init
        final var anotherLocal = ThreadLocal.withInitial(() -> "hello world");
        anotherLocal.remove();
    }
}
