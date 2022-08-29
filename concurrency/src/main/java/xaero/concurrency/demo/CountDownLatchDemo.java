package xaero.concurrency.demo;

import lombok.extern.java.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static io.vavr.control.Try.run;
import static java.util.stream.Stream.iterate;

@Log
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        final var threadsCount = 10;
        final var countDownLatch = new CountDownLatch(threadsCount);
        final var executorService = Executors.newFixedThreadPool(2);

        iterate(1, i -> i + 1)
                .limit(threadsCount)
                .map(i -> getRunnable(countDownLatch, i))
                .forEach(executorService::submit);

        log.info("wait for finishing threads");

        // countDownLatch.await(); - waits permanent until threads will end

        // waits with timeout, when time is gone - current thread resumes and threads in executor will not stop their work
        final var awaitResult = countDownLatch.await(5L, TimeUnit.SECONDS);
        log.info("threads finished. await result: " + awaitResult);
    }

    private static Runnable getRunnable(CountDownLatch countDownLatch, Integer i) {
        return () -> {
            log.info("run " + i + " thread");

            run(() -> Thread.sleep(5000L));
            countDownLatch.countDown();

            log.info("finish run " + i + " thread");
        };
    }
}
