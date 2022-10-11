package com.georgeracu.demo.springboot.adapter.out;

import com.georgeracu.demo.springboot.port.out.HttpClient;
import com.georgeracu.demo.springboot.port.out.HttpClientConfig;
import com.georgeracu.demo.springboot.port.out.client.NonRetryableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
public class HttpClientImpl implements HttpClient<String> {

    public static final String BACKEND_A = "backendA";
    private final RestTemplate restTemplate;
    private final HttpClientConfig httpClientConfig;

    public HttpClientImpl(final RestTemplate restTemplate, final HttpClientConfig httpClientConfig) {
        this.restTemplate = restTemplate;
        this.httpClientConfig = httpClientConfig;
    }

    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "circuitBreakerFallback")
    @Retry(name = BACKEND_A, fallbackMethod = "retryFallback")
    @Override
    public Optional<String> getObject() {
        return Optional.ofNullable(restTemplate.getForObject(String.format("%s/object", httpClientConfig.getBaseUrl()), String.class));
    }

    @Override
    public Optional<String> circuitBreakerFallback(Throwable t) {
        log.error("Fallback from circuit breaker", t);
        return Optional.empty();
    }

    @Override
    public Optional<String> retryFallback(Throwable t) {
        log.error("Fallback from retry", t);
        throw new NonRetryableException(t.getMessage());
    }
}
