package xaero.concurrency.demo;

import io.vavr.control.Try;
import lombok.extern.java.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

@Log
public class PhaserDemo {

    public static void main(String[] args) {
        final var phaser = new Phaser(1); // current thread should be too for orchestration
        final var executorService = Executors.newFixedThreadPool(10);

        executorService.submit(getRunnable(phaser));
        executorService.submit(getRunnable(phaser));

        phaser.arriveAndAwaitAdvance();
        log.info("all threads started their work");

        phaser.arriveAndAwaitAdvance();
        log.info("finish work");

        phaser.arriveAndDeregister();
        executorService.shutdown();
    }

    private static Runnable getRunnable(Phaser phaser) {
        return () -> {
            phaser.register();

            log.info("thread registered");
            Try.run(() -> Thread.sleep(10000L));
            log.info("thread woke up and wait for others");

            phaser.arriveAndAwaitAdvance();
            log.info("thread is continuing his work");

            Try.run(() -> Thread.sleep(5000L));
            phaser.arriveAndDeregister();
        };
    }
}
