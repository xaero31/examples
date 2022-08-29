package xaero.concurrency.demo;

import io.vavr.control.Try;
import lombok.extern.java.Log;

import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;

@Log
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {
        final var executorService = newScheduledThreadPool(4);

        log.info("schedule runnable");
        executorService.schedule(getRunnable("runnable"), 10L, SECONDS);

        log.info("schedule fixed rate runnable");
        executorService.scheduleAtFixedRate(getRunnable("fixed rate runnable"), 10L, 5L, SECONDS);

        log.info("schedule fixed delay runnable");
        executorService.scheduleWithFixedDelay(getRunnable("fixed delay runnable"), 10L, 5L, SECONDS);
    }

    private static Runnable getRunnable(String runnableName) {
        return () -> {
            log.info("start scheduled " + runnableName);
            Try.run(() -> Thread.sleep(3000L));
            log.info("end scheduled " + runnableName);
        };
    }
}
