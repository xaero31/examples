package xaero.concurrency.demo;

import lombok.extern.java.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.StampedLock;

@Log
public class LockDemo {

    public static void main(String[] args) {

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
