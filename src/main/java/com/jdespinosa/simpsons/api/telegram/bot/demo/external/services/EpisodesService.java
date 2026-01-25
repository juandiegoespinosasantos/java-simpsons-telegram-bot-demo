package com.jdespinosa.simpsons.api.telegram.bot.demo.external.services;

import com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient.IEpisodesApiRestClient;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiEpisodeDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 09, 2026
 * @since 17
 */
@Service
@AllArgsConstructor
public class EpisodesService implements IEpisodesService {

    private final IEpisodesApiRestClient apiClient;

    @Override
    public SimpsonsApiPageDTO<SimpsonsApiEpisodeDTO> findAll(final int page) {
        return apiClient.findAll(page);
    }

    @Override
    public SimpsonsApiEpisodeDTO findById(final Long id) {
        return apiClient.findById(id);
    }
}