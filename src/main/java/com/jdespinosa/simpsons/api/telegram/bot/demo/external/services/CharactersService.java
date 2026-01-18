package com.jdespinosa.simpsons.api.telegram.bot.demo.external.services;

import com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient.ICharactersApiRestClient;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiCharacterDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 17, 2026
 * @since 17
 */
@Service
@AllArgsConstructor
public class CharactersService implements ICharactersService {

    private final ICharactersApiRestClient apiClient;

    @Override
    public SimpsonsApiPageDTO<SimpsonsApiCharacterDTO> findAll(final int page) {
        return apiClient.findAll(page);
    }

    @Override
    public SimpsonsApiCharacterDTO findById(final Long id) {
        return apiClient.findById(id);
    }
}