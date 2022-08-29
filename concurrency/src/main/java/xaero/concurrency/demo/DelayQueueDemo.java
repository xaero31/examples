package xaero.concurrency.demo;

import lombok.extern.java.Log;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

@Log
public class DelayQueueDemo {

    /**
     * delay queue contains only Delayed objects and by take() or other methods returns only objects whose delay
     * are already expired
     */
    public static void main(String[] args) {
        final var delayQueue = new DelayQueue<Delayed>();
    }
}
