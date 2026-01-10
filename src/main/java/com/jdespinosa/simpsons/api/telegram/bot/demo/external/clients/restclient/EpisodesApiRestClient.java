package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiEpisodeDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiEpisodesPageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 09, 2026
 * @since 17
 */
@Repository
@AllArgsConstructor
public class EpisodesApiRestClient implements IEpisodesApiRestClient {

    private final RestClient simpsonsApiRestClient;

    @Override
    public SimpsonsApiEpisodesPageDTO findAll(final int page) {
        return simpsonsApiRestClient.get()
                .uri("/episodes?page={page}", page)
                .retrieve()
                .body(SimpsonsApiEpisodesPageDTO.class);
    }

    @Override
    public SimpsonsApiEpisodeDTO findById(final Long id) {
        return simpsonsApiRestClient.get()
                .uri("/episodes/{id}", id)
                .retrieve()
                .body(SimpsonsApiEpisodeDTO.class);
    }
}