package xaero.concurrency.demo;

import io.vavr.control.Try;
import lombok.extern.java.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

@Log
public class LockDemo {

    public static void main(String[] args) {
        testReentrantLockWithFewEntries();
    }

    /**
     * demonstrates that if we got a lock 2 times from one thread - then we could unlock it 2 times too for completely
     * free the lock
     */
    private static void testReentrantLockWithFewEntries() {
        final var lock = new ReentrantLock();
        final var executorService = Executors.newFixedThreadPool(4);

        final Runnable runnable = () -> {
            try {
                log.info("start thread [" + Thread.currentThread().getName() + "]");

                lock.lock();
                log.info("got lock [" + Thread.currentThread().getName() + "]");
                Try.run(() -> Thread.sleep(15000L));

                try {
                    lock.lock();
                    log.info("got second lock [" + Thread.currentThread().getName() + "]");
                    Try.run(() -> Thread.sleep(15000L));
                } finally {
                    lock.unlock();
                    log.info("free second lock [" + Thread.currentThread().getName() + "]");
                    Try.run(() -> Thread.sleep(15000L));
                }
            } finally {
                lock.unlock();
                log.info("free lock [" + Thread.currentThread().getName() + "]");
                Try.run(() -> Thread.sleep(15000L));
            }
        };

        executorService.submit(runnable);
        executorService.submit(runnable);
    }

    private void lockMethods(Lock lock) throws InterruptedException {
        try {
            // acquire lock or block until it will be available to acquire
            lock.lock();

            // same as lock, but allow blocked thread to be interrupted
            lock.lockInterruptibly();

            // non-block version, return true if success
            lock.tryLock();

            // same but with some waiting amount of time
            lock.tryLock(5L, TimeUnit.SECONDS);
        } finally {
            // unlock, obviously
            lock.unlock();
        }
    }

    private void readWriteLock(ReadWriteLock readWriteLock) {
        final var readLock = readWriteLock.readLock();
        final var writeLock = readWriteLock.writeLock();
    }

    private void stampedLock(StampedLock stampedLock) {
        long readLockStamp = 0;
        long writeLockStamp = 0;
        long optimisticLockStamp = 0;

        try {
            readLockStamp = stampedLock.readLock();
            writeLockStamp = stampedLock.writeLock();

            final var isValid = stampedLock.validate(optimisticLockStamp);
        } finally {
            stampedLock.unlockRead(readLockStamp);
            stampedLock.unlockWrite(writeLockStamp);
            stampedLock.unlock(optimisticLockStamp);
        }
    }
}
