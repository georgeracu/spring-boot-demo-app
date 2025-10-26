package com.georgeracu.demo.springboot.adapter.out.client.google;

import com.georgeracu.demo.springboot.port.out.client.google.GoogleClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleClientConfigImpl implements GoogleClientConfig {

    private final String url;

    public GoogleClientConfigImpl(@Value("${google.client.url}") String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

}
