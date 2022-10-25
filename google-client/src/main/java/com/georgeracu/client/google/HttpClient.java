package com.georgeracu.client.google;

import java.util.Optional;

public interface HttpClient<T> {

    Optional<T> getObject();

    Optional<T> circuitBreakerFallback(Throwable t);

    Optional<String> retryFallback(Throwable t);
}
