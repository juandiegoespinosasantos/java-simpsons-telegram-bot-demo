package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

import java.net.URI;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 09, 2026
 * @since 17
 */
@Configuration
@Slf4j
public class RestClientConfig {

    @Value("${simpsons.api.base-url}")
    private String simpsonsApiBaseUrl;

    @Bean
    public RestClient simpsonsApiRestClient() {
        return RestClient.builder()
                .requestInterceptor(getRequestInterceptor())
                .baseUrl(simpsonsApiBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private ClientHttpRequestInterceptor getRequestInterceptor() {
        return (request, body, execution) -> {
            long start = System.currentTimeMillis();

            HttpMethod method = request.getMethod();
            URI uri = request.getURI();
            log.info("API request >> {} {}", method, uri);

            ClientHttpResponse execute = execution.execute(request, body);
            long end = System.currentTimeMillis();
            log.info("API response {} ({}ms) << {} {}", execute.getStatusCode(), (end - start), method, uri);

            return execute;
        };
    }
}