package xaero.concurrency.demo;

import lombok.extern.java.Log;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

import static io.vavr.control.Try.run;
import static java.util.stream.Stream.iterate;

@Log
public class CyclicBarrierDemo {

    /**
     * create barrier with threadsCount (parties) and (optional) runnable
     * when count of threads which invoked 'await' on barrier becomes equal parties count from barrier - then
     * all those threads resumes and if (optional) runnable was present - in runs
     */
    public static void main(String[] args) {
        final var threadsCount = 10;
        final var barrier = new CyclicBarrier(threadsCount, () -> log.info("barrier run"));
        final var executorService = Executors.newFixedThreadPool(threadsCount);

        iterate(1, i -> i + 1)
                .limit(threadsCount)
                .map(i -> getRunnable(barrier, i))
                .forEach(executorService::submit);
    }

    private static Runnable getRunnable(CyclicBarrier barrier, Integer i) {
        return () -> {
            log.info("run " + i + " thread");

            run(() -> barrier.await());
            run(() -> Thread.sleep(5000L));

            log.info("finish run " + i + " thread");
        };
    }
}
