package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiCharacterDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 17, 2026
 * @since 17
 */
@Repository
@AllArgsConstructor
public class CharactersApiRestClient implements ICharactersApiRestClient {

    private final RestClient simpsonsApiRestClient;

    @Override
    public SimpsonsApiPageDTO<SimpsonsApiCharacterDTO> findAll(final int page) {
        return simpsonsApiRestClient.get()
                .uri("/characters?page={page}", page)
                .retrieve()
                .body(SimpsonsApiPageDTO.class);
    }

    @Override
    public SimpsonsApiCharacterDTO findById(final Long id) {
        return simpsonsApiRestClient.get()
                .uri("/characters/{id}", id)
                .retrieve()
                .body(SimpsonsApiCharacterDTO.class);
    }
}