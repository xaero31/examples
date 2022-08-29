package xaero.concurrency.demo;

import io.vavr.control.Try;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * analogue to wait-notify-notifyAll, but with lock
 */
@Log
public class ConditionsDemo {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    private static final Lock LOCK = new ReentrantLock();

    private static final Condition EMPTY_LIST_CONDITION = LOCK.newCondition();
    private static final Condition FILLED_LIST_CONDITION = LOCK.newCondition();

    private static final List<String> LIST = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            addString();
            removeString();
        }
    }

    private static void addString() {
        EXECUTOR_SERVICE.submit(() -> {
            try {
                LOCK.lock();
                while (!LIST.isEmpty()) {
                    Try.run(EMPTY_LIST_CONDITION::await);
                }
                LIST.add(UUID.randomUUID().toString());
                log.info("string added");
                FILLED_LIST_CONDITION.signal();
            } finally {
                LOCK.unlock();
            }
        });
    }

    private static void removeString() {
        EXECUTOR_SERVICE.submit(() -> {
            try {
                LOCK.lock();
                while (LIST.isEmpty()) {
                    Try.run(FILLED_LIST_CONDITION::await);
                }
                LIST.remove(0);
                log.info("removed string");
                EMPTY_LIST_CONDITION.signal();
            } finally {
                LOCK.unlock();
            }
        });
    }
}
