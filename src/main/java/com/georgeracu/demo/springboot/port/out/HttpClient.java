package com.georgeracu.demo.springboot.port.out;

import java.util.Optional;

public interface HttpClient<T> {

    Optional<T> getObject();

    Optional<T> circuitBreakerFallback(Throwable t);

    Optional<String> retryFallback(Throwable t);
}
