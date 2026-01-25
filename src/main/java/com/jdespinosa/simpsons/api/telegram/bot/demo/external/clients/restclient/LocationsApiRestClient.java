package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiLocationDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 24, 2026
 * @since 17
 */
@Repository
public class LocationsApiRestClient extends ApiRestClient implements ILocationsApiRestClient {

    protected LocationsApiRestClient(RestClient simpsonsApiRestClient) {
        super(simpsonsApiRestClient);
    }

    @Override
    protected String getPath() {
        return "/locations";
    }

    @Override
    public SimpsonsApiPageDTO<SimpsonsApiLocationDTO> findAll(final int page) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", page);

        return getRequest(queryParams, SimpsonsApiPageDTO.class);
    }

    @Override
    public SimpsonsApiLocationDTO findById(final Long id) {
        return getRequest(id, SimpsonsApiLocationDTO.class);
    }
}