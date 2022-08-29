package xaero.concurrency.demo;

import lombok.extern.java.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static io.vavr.control.Try.run;
import static java.lang.Thread.sleep;

@Log
public class SemaphoreDemo {

    /**
     * semaphore is like a bottleneck for threads
     */
    public static void main(String[] args) {
        final var semaphore = new Semaphore(5);
        final var executorService = Executors.newFixedThreadPool(10);

        while (true) {
            executorService.submit(getRunnable(semaphore));
            run(() -> sleep(5000L));
        }
    }

    private static Runnable getRunnable(Semaphore semaphore) {
        return () -> run(() -> {
            semaphore.acquire(); // can be with few permits or with timeout with tryAcquire
            log.info("new thread. semaphore available permits: " + semaphore.availablePermits());
            run(() -> sleep(10000L));
        }).andFinally(() -> {
            semaphore.release();
            log.info("one more free semaphore. current available permits: " + semaphore.availablePermits());
        });
    }
}
