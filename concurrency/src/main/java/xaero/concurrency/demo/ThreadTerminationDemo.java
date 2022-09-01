package xaero.concurrency.demo;

import io.vavr.control.Try;
import lombok.extern.java.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

@Log
public class ThreadTerminationDemo {

    public static void main(String[] args) throws InterruptedException {
        testSleepInterruption();
        testLockedInterruption();
    }

    /**
     * when thread is sleeping we can interrupt it
     */
    private static void testSleepInterruption() {
        final Runnable sleepRunnable = () -> Try.run(() -> TimeUnit.MINUTES.sleep(5L))
                .onFailure(throwable -> log.info("runnable exception:\n" + getStackTrace(throwable)));
        final var sleepThread = new Thread(sleepRunnable);
        sleepThread.start();
        sleepThread.interrupt();

        log.info("is interrupted: [" + sleepThread.isInterrupted() + "]");
    }

    /**
     * when thread is waiting for mutex, interrupt will not work
     */
    private static void testLockedInterruption() throws InterruptedException {
        final var lock = new ReentrantLock();
        final Runnable mutexRunnable = () -> Try.run(() -> {
                    log.info("thread start " + Thread.currentThread().getName());
                    lock.lock();
                    log.info("locked mutex by " + Thread.currentThread().getName());
                    TimeUnit.MINUTES.sleep(5L);
                })
                .onFailure(throwable -> log.info("runnable exception:\n" + getStackTrace(throwable)))
                .andFinally(lock::unlock);

        final var firstThread = new Thread(mutexRunnable);
        final var secondThread = new Thread(mutexRunnable);

        firstThread.start();
        TimeUnit.SECONDS.sleep(5L);
        secondThread.start();
        TimeUnit.SECONDS.sleep(5L);
        secondThread.interrupt();

        log.info("first thread is interrupted: " + firstThread.isInterrupted());
        log.info("second thread is interrupted: " + secondThread.isInterrupted());
    }
}
