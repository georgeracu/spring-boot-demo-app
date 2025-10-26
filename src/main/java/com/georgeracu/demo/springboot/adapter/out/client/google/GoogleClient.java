package com.georgeracu.demo.springboot.adapter.out.client.google;

import com.georgeracu.demo.springboot.port.out.client.google.GoogleClientConfig;
import com.georgeracu.demo.springboot.port.out.client.google.GoogleClientPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
public final class GoogleClient implements GoogleClientPort<String> {

    private final RestTemplate restTemplate;
    private final GoogleClientConfig clientConfig;

    public GoogleClient(final RestTemplate restTemplate,
                        final GoogleClientConfig clientConfig) {
        this.restTemplate = restTemplate;
        this.clientConfig = clientConfig;
    }

    @Override
    public Optional<String> getSearchResults(String query) {
        log.info("GoogleClient.getSearchResults: {}", query);
        String response = restTemplate.getForObject(clientConfig.getUrl(), String.class, query);
        return Optional.ofNullable(response);
    }

    @Override
    public Optional<String> fallback(String query, Exception e) {
        return Optional.empty();
    }
}
