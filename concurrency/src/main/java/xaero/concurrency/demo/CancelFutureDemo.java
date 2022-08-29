package xaero.concurrency.demo;

import io.vavr.control.Try;
import lombok.extern.java.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@Log
public class CancelFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final var executorService = Executors.newSingleThreadExecutor();
        final Callable<String> callable = () -> {
            log.info("start callable");
            Try.run(() -> Thread.sleep(15000L)).onFailure(e -> log.severe("error callable " + e));
            log.info("end callable");
            return "result";
        };

        final var future = executorService.submit(callable);
        future.cancel(true);

        log.info("callable is cancelled: " + future.isCancelled());
        log.info("callable is done: " + future.isDone());

        if (future.isDone() && !future.isCancelled()) {
            log.info("callable result: " + future.get());
        }
    }
}
