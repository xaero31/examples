package xaero.concurrency.demo;

import io.vavr.control.Try;
import lombok.extern.java.Log;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.joining;

@Log
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final var executorService = Executors.newSingleThreadExecutor();
        // like runnable
        runAsync(() -> log.info("invoke async"), executorService);

        // like callable
        supplyAsync(() -> "invoked async", executorService)
                // from arg return new completable future
                .thenCompose(s -> supplyAsync(() -> s))
                // handle results of both completable futures
                .thenCombine(supplyAsync(() -> "second async"), (x, y) -> x + " " + y)
                // just an action with both futures
                // .thenAcceptBoth(supplyAsync(() -> "third async"), (x, y) -> log.info("both complete"))

                // just an action with the result
                .thenAccept(result -> log.info("completable future result async: " + result))
                // just to synchronize main method flow
                .get();

        log.info("start completing futures in parallel");
        CompletableFuture.allOf(
                runAsync(() -> Try.run(() -> Thread.sleep(3000L))),
                runAsync(() -> Try.run(() -> Thread.sleep(1000L))),
                runAsync(() -> Try.run(() -> Thread.sleep(5000L)))
        ).thenRun(() -> log.info("all futures are complete")).get();

        log.info("start completing futures in parallel and join results");
        log.info(Stream.of(
                        supplyAsync(() -> "first future"),
                        supplyAsync(() -> "second future"),
                        supplyAsync(() -> "third future"))
                .map(CompletableFuture::join)
                .collect(joining(" - ")));

        // handle the exception
        supplyAsync(() -> "hello world")
                .thenApply(result -> {
                    throw new RuntimeException();
                })
                .handle((result, exception) -> {
                    log.info("exception: " + exception);
                    return result;
                });

        // invocation of .get() will end with a runtime exception - we could extract future to variable before
        supplyAsync(() -> "hello world")
                .completeExceptionally(new RuntimeException());

        supplyAsync(() -> "hello world")
                .newIncompleteFuture(); // idk how i can use this

        supplyAsync(() -> "hello world")
                .copy(); // same

        supplyAsync(() -> "hello world")
                .minimalCompletionStage();

        // CompletableFuture.delayedExecutor()
        // CompletableFuture.completedStage(value)
        // CompletableFuture.completedFuture(value)

        // run completable future with a delay
        supplyAsync(() -> "hello world", CompletableFuture.delayedExecutor(1L, TimeUnit.SECONDS));

        // return default value if completable future not complete until timeout
        supplyAsync(() -> "hello world").completeOnTimeout("timeout value", 1L, TimeUnit.SECONDS);

        // throw timeout exception if not complete until timeout
        supplyAsync(() -> "hello world").orTimeout(1L, TimeUnit.SECONDS);
    }
}
