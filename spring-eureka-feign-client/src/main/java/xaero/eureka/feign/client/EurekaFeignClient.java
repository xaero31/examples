package xaero.eureka.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * created by Nikita_Ermakov at 7/1/2022
 */
@FeignClient("eurekaservice/eureka")
public interface EurekaFeignClient {

    @GetMapping
    String getTestValue();
}
