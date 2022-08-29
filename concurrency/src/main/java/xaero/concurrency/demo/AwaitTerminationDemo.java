package xaero.concurrency.demo;

import io.vavr.control.Try;
import lombok.extern.java.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Stream.iterate;

@Log
public class AwaitTerminationDemo {

    public static void main(String[] args) throws InterruptedException {
        final var executorService = Executors.newFixedThreadPool(4);

        // just generate and schedule some runnables
        iterate(0, i -> i < 8, i -> i + 1)
                .map(i -> (Runnable) () -> {
                    log.info("run thread " + Thread.currentThread().getName());
                    Try.run(() -> Thread.sleep(10000L));
                })
                .forEach(executorService::submit);

        executorService.shutdown();

        /*
          awaitTermination method waits for the end of all threads that was scheduled in executor service or for timeout
          and returns 'true' if all tasks are complete
         */
        final var isTerminated = executorService.awaitTermination(60L, TimeUnit.SECONDS);
        log.info("termination result: " + isTerminated);

        if (!isTerminated) {
            // force executor service shutdown
            log.info("shutdown now");
            executorService.shutdownNow();
        }
    }
}
