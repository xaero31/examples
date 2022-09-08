package xaero.collection;

import io.vavr.control.Try;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.stream.Stream;

/**
 * transfer queue producer will be blocked until any of consumers gets the item
 */
public class TransferQueueDemo {

    public static void main(String[] args) {
        final TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
        final var executorService = Executors.newFixedThreadPool(2);
        final var transferFuture = executorService.submit(() -> Try.run(() -> transfer(transferQueue)));

        executorService.submit(() -> Try.run(() -> {
            while (!transferFuture.isDone()) {
                if (!transferQueue.isEmpty()) {
                    System.out.println("consumed value: " + transferQueue.take());
                }
            }

            executorService.shutdown();
        }));
    }

    private static void transfer(TransferQueue<String> transferQueue) {
        Stream.iterate(0, i -> i + 1)
                .limit(10)
                .forEach(i -> Try.run(() -> transferQueue.transfer(i + "-" + UUID.randomUUID())));
        System.out.println("transfered");
    }
}
