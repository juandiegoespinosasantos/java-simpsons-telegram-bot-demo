package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiEpisodeDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 09, 2026
 * @since 17
 */
@Repository
public class EpisodesApiRestClient extends ApiRestClient implements IEpisodesApiRestClient {

    protected EpisodesApiRestClient(RestClient simpsonsApiRestClient) {
        super(simpsonsApiRestClient);
    }

    @Override
    protected String getPath() {
        return "/episodes";
    }

    @Override
    public SimpsonsApiPageDTO<SimpsonsApiEpisodeDTO> findAll(final int page) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", page);

        return getRequest(queryParams, SimpsonsApiPageDTO.class);
    }

    @Override
    public SimpsonsApiEpisodeDTO findById(final Long id) {
        return getRequest(id, SimpsonsApiEpisodeDTO.class);
    }
}