package com.jdespinosa.simpsons.api.telegram.bot.demo.external.services;

import com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient.ILocationsApiRestClient;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiLocationDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 24, 2026
 * @since 17
 */
@Service
@AllArgsConstructor
public class LocationsService implements ILocationsService {

    private final ILocationsApiRestClient apiClient;

    @Override
    public SimpsonsApiPageDTO<SimpsonsApiLocationDTO> findAll(final int page) {
        return apiClient.findAll(page);
    }

    @Override
    public SimpsonsApiLocationDTO findById(final Long id) {
        return apiClient.findById(id);
    }
}