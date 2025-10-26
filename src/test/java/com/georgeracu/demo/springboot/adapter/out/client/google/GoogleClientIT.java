package com.georgeracu.demo.springboot.adapter.out.client.google;


import com.georgeracu.demo.springboot.WireMockInitializer;
import com.georgeracu.demo.springboot.port.out.client.google.GoogleClientConfig;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;


//@ContextConfiguration(initializers = {WireMockInitializer.class})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
class GoogleClientIT {
    private static WireMockServer wireMockServer;

    @BeforeAll
    static void init() {
        wireMockServer = new WireMockServer(
                new WireMockConfiguration().dynamicPort()
        );
        wireMockServer.start();
    }

    @AfterAll
    static void tearDown() {
        wireMockServer.stop();
    }

    @BeforeEach
    void setup() {
        wireMockServer.resetAll();
        String tscUrl = wireMockServer.baseUrl();
    }

//    @Autowired
    private GoogleClient googleClient;
//    private final GoogleClientConfig clientConfig = Mockito.mock(GoogleClientConfig.class);
//    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

//    @BeforeEach
//    void setUp() {
//        Mockito.reset(clientConfig, restTemplate);
//        googleClient = new GoogleClient(restTemplate, clientConfig);
//    }

    @Disabled
    @Test
    void shouldReturnResponseWhenValidQuery() {
        // arrange
        String query = "test";
        String url = "https://example.com?q={query}";
        stubFor(get("/?q=test").willReturn(ok()));
//        when(clientConfig.getUrl()).thenReturn(url);
//        when(restTemplate.getForObject(url, String.class, query)).thenReturn("response");

        // act
        Optional<String> maybeResults = googleClient.getSearchResults(query);

        // assert
        assertThat(maybeResults).isPresent()
                .hasValue("response");
//        verify(clientConfig).getUrl();
//        verify(restTemplate).getForObject(url, String.class, query);
    }

//    @Test
//    void shouldRetryOnServerError() {
//        // arrange
//        String query = "test";
//        String url = "https://example.com?q={query}";
//        when(clientConfig.getUrl()).thenReturn(url);
//        when(restTemplate.getForObject(url, String.class, query)).thenThrow(new RuntimeException("Server Error"));
//
//        // act
//        Optional<String> maybeResults = googleClient.getSearchResults(query);
//
//        // assert
//        assertThatThrownBy(() -> googleClient.getSearchResults(query))
//                .isInstanceOf(RuntimeException.class)
//                .hasMessage("Server Error");
//        verify(clientConfig).getUrl();
//        verify(restTemplate).getForObject(url, String.class, query);
//    }
}