package com.hermes.util;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Supplier;

@Getter
public enum ServerUrlType {
    CRAWLING(() -> WebClient.builder().baseUrl("http://localhost:3000").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build()),
    HERMES(() -> WebClient.builder().baseUrl("http://localhost:8080").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build());
    private Supplier<WebClient> webClient;

    ServerUrlType(Supplier<WebClient> webClient) {
        this.webClient = webClient;
    }
}
