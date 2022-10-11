package com.georgeracu.demo.springboot.adapter.out;

import com.georgeracu.demo.springboot.port.out.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfigImpl implements HttpClientConfig {

    private final String baseUrl;

    public HttpClientConfigImpl(@Value("${clients.google.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }
}
