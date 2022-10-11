package com.georgeracu.demo.springboot.adapter.out;

import com.georgeracu.demo.springboot.MainApplication;
import com.georgeracu.demo.springboot.WireMockInitializer;
import com.georgeracu.demo.springboot.port.room.RoomsRepository;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.event.RetryEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.georgeracu.demo.springboot.adapter.out.HttpClientImpl.BACKEND_A;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(classes = {MainApplication.class}, webEnvironment = RANDOM_PORT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ContextConfiguration(initializers = {WireMockInitializer.class})
class HttpClientImplTest {

    @MockBean
    private RoomsRepository roomsRepository;

    @Autowired
    private RetryRegistry retryRegistry;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    private WireMockServer wireMockServer;
    @Autowired
    private HttpClientImpl httpClient;
    private List<RetryEvent> successRetryEvents;
    private List<RetryEvent> retryErrorEvents;
    private List<RetryEvent> retryRetryEvents;

    @BeforeEach
    void setupEach() {
        wireMockServer.resetAll();
        successRetryEvents = new ArrayList<>();
        retryErrorEvents = new ArrayList<>();
        retryRetryEvents = new ArrayList<>();
        retryRegistry.retry(BACKEND_A).getEventPublisher().onSuccess(successRetryEvents::add);
        retryRegistry.retry(BACKEND_A).getEventPublisher().onError(retryErrorEvents::add);
        retryRegistry.retry(BACKEND_A).getEventPublisher().onRetry(retryRetryEvents::add);

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(BACKEND_A);
        if (circuitBreaker.getState() != CircuitBreaker.State.CLOSED) {
            circuitBreaker.transitionToClosedState();
        }
    }

    @Test
    void shouldGetObject() {
        // arrange
        wireMockServer.stubFor(get(urlEqualTo("/object"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("Hello World")));

        // act
        final Optional<String> maybeObject = httpClient.getObject();

        // assert
        assertThat(maybeObject).isPresent()
                .contains("Hello World");
    }

    @DisplayName("should fail retrying 3 times and then succeed on the 4th attempt")
    @Test
    void shouldRetryWhenFailingToGetObject() {
        // arrange
        wireMockServer.stubFor(get(urlEqualTo("/object"))
                .willReturn(serverError()
                        .withBody("Call failed")));

        // act
        final Optional<String> maybeObject = httpClient.getObject();

        // assert
        assertThat(maybeObject).isEmpty();
        assertThat(retryErrorEvents).hasSize(1);
        assertThat(successRetryEvents).isEmpty();
        assertThat(retryRetryEvents).hasSize(2);

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(BACKEND_A);
        CircuitBreaker.Metrics circuitBreakerMetrics = circuitBreaker.getMetrics();
        assertThat(circuitBreakerMetrics.getNumberOfFailedCalls()).isEqualTo(1L);
        assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);

        wireMockServer.stubFor(get(urlEqualTo("/object"))
                .willReturn(ok()
                        .withBody("Hello World")));


        // act
        final Optional<String> maybeSuccess = httpClient.getObject();

        // assert
        assertThat(maybeSuccess).isPresent()
                .contains("Hello World");
    }

    @DisplayName("should open the circuit breaker after exhausting all attempts")
    @Test
    void shouldOpenCircuitBreakerWhenFailingToGetObject() {
        // arrange
        wireMockServer.stubFor(get(urlEqualTo("/object"))
                .willReturn(serverError()
                        .withBody("Call failed")));

        // act
        for(int i = 0; i < 6; i++) {
            final Optional<String> maybeObject = httpClient.getObject();
            assertThat(maybeObject).isEmpty();
        }
        // assert

        assertThat(retryErrorEvents).hasSize(5);
        assertThat(successRetryEvents).isEmpty();
        assertThat(retryRetryEvents).hasSize(10);

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(BACKEND_A);
        CircuitBreaker.Metrics circuitBreakerMetrics = circuitBreaker.getMetrics();
        assertThat(circuitBreakerMetrics.getNumberOfFailedCalls()).isEqualTo(5L);
        assertThat(circuitBreakerMetrics.getNumberOfNotPermittedCalls()).isEqualTo(1L);
        assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.OPEN);

    }
}