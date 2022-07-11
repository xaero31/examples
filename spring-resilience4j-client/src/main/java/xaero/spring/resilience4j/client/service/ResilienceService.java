package xaero.spring.resilience4j.client.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xaero.spring.resilience4j.client.feign.ResilienceServiceClient;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@Service
public class ResilienceService {

    private final ResilienceServiceClient client;

    private long count;

    public ResilienceService(ResilienceServiceClient client) {
        this.client = client;
    }

    /**
     * resilience4j has different annotations for different patterns. look documentation
     * but time limiter required the async return type like 'Mono' or 'CompletableFuture'
     */
    @TimeLimiter(name = "hystrix-service", fallbackMethod = "getWithTimeoutFallback")
    @CircuitBreaker(name = "hystrix-service", fallbackMethod = "getWithTimeoutFallback")
    public CompletableFuture<String> getWithTimeout() {
        log.info("invoked get with timeout: {}", count);
        final CompletableFuture<String> result = supplyAsync(() -> client.getWithTimeout(count));

        count++;
        return result;
    }

    @CircuitBreaker(name = "hystrix-service", fallbackMethod = "getWithErrorFallback")
    public String getWithError() {
        log.info("invoked get with error: {}", count);
        final String result = client.getWithError(count);

        count++;
        return result;
    }

    @CircuitBreaker(name = "hystrix-service", fallbackMethod = "getWithErrorAndParamFallback")
    public String getWithErrorAndParam(Long id) {
        log.info("invoked get with error and param: {}", id);
        return client.getWithError(id);
    }

    @CircuitBreaker(name = "hystrix-service", fallbackMethod = "getSuccessFallback")
    public String getSuccess() {
        log.info("invoked get success: {}", count);
        final String result = client.getSuccess(count);

        count++;
        return result;
    }

    private CompletableFuture<String> getWithTimeoutFallback(Exception e) {
        log.info("invoked get with timeout fallback: {}", count);
        return completedFuture("fallback");
    }

    private String getWithErrorFallback(Exception e) {
        log.info("invoked get with error fallback: {}", count);
        return "fallback";
    }

    private String getWithErrorAndParamFallback(Long id, Exception e) {
        log.info("invoked get with error and param fallback: {}", id);
        return "fallback";
    }

    private String getSuccessFallback(Exception e) {
        log.info("invoked get success fallback: {}", count);
        return "fallback";
    }
}
