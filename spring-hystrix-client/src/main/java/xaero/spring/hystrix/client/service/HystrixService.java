package xaero.spring.hystrix.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xaero.spring.hystrix.client.feign.HystrixServiceClient;

@Slf4j
@Service
public class HystrixService {

    private final HystrixServiceClient client;

    private long count;

    public HystrixService(HystrixServiceClient client) {
        this.client = client;
    }

    @HystrixCommand(fallbackMethod = "getWithTimeoutFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "100")
            })
    public String getWithTimeout() {
        log.info("invoked get with timeout: {}", count);
        final String result = client.getWithTimeout(count);

        count++;
        return result;
    }

    @HystrixCommand(fallbackMethod = "getWithErrorFallback",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "100")
            })
    public String getWithError() {
        log.info("invoked get with error: {}", count);
        final String result = client.getWithError(count);

        count++;
        return result;
    }

    @HystrixCommand(fallbackMethod = "getSuccessFallback")
    public String getSuccess() {
        log.info("invoked get success: {}", count);
        final String result = client.getSuccess(count);

        count++;
        return result;
    }

    private String getWithTimeoutFallback() {
        log.info("invoked get with timeout fallback: {}", count);
        return "fallback";
    }

    private String getWithErrorFallback() {
        log.info("invoked get with error fallback: {}", count);
        return "fallback";
    }

    private String getSuccessFallback() {
        log.info("invoked get success fallback: {}", count);
        return "fallback";
    }
}
