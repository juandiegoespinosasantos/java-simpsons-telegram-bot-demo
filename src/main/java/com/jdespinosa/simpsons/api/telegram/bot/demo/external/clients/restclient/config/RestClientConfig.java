package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 09, 2026
 * @since 17
 */
@Configuration
public class RestClientConfig {

    @Value("${simpsons.api.base-url}")
    private String simpsonsApiBaseUrl;

    @Bean
    public RestClient simpsonsApiRestClient() {
        return RestClient.builder()
                .baseUrl(simpsonsApiBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}