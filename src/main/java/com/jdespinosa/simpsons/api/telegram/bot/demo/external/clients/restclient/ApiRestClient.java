package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.StringJoiner;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 24, 2026
 * @since 17
 */
public abstract class ApiRestClient {

    private final RestClient simpsonsApiRestClient;

    protected ApiRestClient(RestClient simpsonsApiRestClient) {
        this.simpsonsApiRestClient = simpsonsApiRestClient;
    }

    protected abstract String getPath();

    protected <R extends Object> R getRequest(final Map<String, Object> queryParams,
                                              final Class<R> responseType) {
        String uri = buildUri(queryParams);

        return retrieve(uri, responseType);
    }

    protected <R extends Object> R getRequest(final Object pathVariable, final Class<R> responseType) {
        String uri = getPath() + '/' + pathVariable;

        return retrieve(uri, responseType);
    }

    private String buildUri(final Map<String, Object> queryParams) {
        return getPath() + buildQueryParams(queryParams);
    }

    private String buildQueryParams(final Map<String, Object> queryParams) {
        if (CollectionUtils.isEmpty(queryParams)) return "";

        StringJoiner sj = new StringJoiner(";");

        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            sj.add(entry.getKey() + '=' + entry.getValue());
        }

        return "?" + sj;
    }

    private <R extends Object> R retrieve(final String uri, final Class<R> responseType) {
        return simpsonsApiRestClient.get()
                .uri(uri)
                .retrieve()
                .body(responseType);
    }
}