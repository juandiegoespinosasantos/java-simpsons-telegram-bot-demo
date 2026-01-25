package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiLocationDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 24, 2026
 * @since 17
 */
@Repository
@AllArgsConstructor
public class LocationsApiRestClient implements ILocationsApiRestClient {

    private final RestClient simpsonsApiRestClient;

    @Override
    public SimpsonsApiPageDTO<SimpsonsApiLocationDTO> findAll(final int page) {
        return simpsonsApiRestClient.get()
                .uri("/locations?page={page}", page)
                .retrieve()
                .body(SimpsonsApiPageDTO.class);
    }

    @Override
    public SimpsonsApiLocationDTO findById(final Long id) {
        return simpsonsApiRestClient.get()
                .uri("/locations/{id}", id)
                .retrieve()
                .body(SimpsonsApiLocationDTO.class);
    }
}