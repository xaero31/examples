package xaero.concurrency.demo;

import lombok.extern.java.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static io.vavr.control.Try.run;
import static java.lang.Thread.sleep;

@Log
public class BlockingQueueDemo {

    /**
     * take() - take from head of blocking queue and blocks if queue is empty
     * put() - blocks if queue is full
     * add() - throws an exception if queue is full
     * offer() - add item to queue and immediately returns boolean with result of operation, not block
     * offer(timeout) - same but waits with timeout
     * poll(timeout) - take item with timeout or returns null after timeout exceed
     */
    public static void main(String[] args) throws InterruptedException {
        final var blockingQueue = new LinkedBlockingQueue<Runnable>(4);
        final var executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            while (true) {
                blockingQueue.put(getRunnable(blockingQueue));
                log.info("put new task. queue size: " + blockingQueue.size());
                run(() -> sleep(2000L));
            }
        });

        while (true) {
            log.info("submitting a task. queue size: " + blockingQueue.size());
            blockingQueue.take().run();
            log.info("submitted a task. queue size: " + blockingQueue.size());
        }
    }

    private static Runnable getRunnable(BlockingQueue<Runnable> queue) {
        return () -> run(() -> {
            log.info("new thread starts. queue size: " + queue.size());
            run(() -> sleep(10000L));
        });
    }
}
