package com.georgeracu.demo.springboot.port.out.client.google;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public interface GoogleClientPort<T> {
    static final String BACKEND_A = "backendA";

    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    @RateLimiter(name = BACKEND_A)
    @Bulkhead(name = BACKEND_A, fallbackMethod = "fallback")
    @Retry(name = BACKEND_A)
    @TimeLimiter(name = BACKEND_A)
    Optional<T> getSearchResults(String query);

    Optional<T> fallback(String query, Exception e);

}
