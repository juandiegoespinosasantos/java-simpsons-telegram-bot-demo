package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiCharacterDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 17, 2026
 * @since 17
 */
@Repository
public class CharactersApiRestClient extends ApiRestClient implements ICharactersApiRestClient {

    public CharactersApiRestClient(RestClient simpsonsApiRestClient) {
        super(simpsonsApiRestClient);
    }

    @Override
    protected String getPath() {
        return "/characters";
    }

    @Override
    public SimpsonsApiPageDTO<SimpsonsApiCharacterDTO> findAll(final int page) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", page);

        return getRequest(queryParams, SimpsonsApiPageDTO.class);
    }

    @Override
    public SimpsonsApiCharacterDTO findById(final Long id) {
        return getRequest(id, SimpsonsApiCharacterDTO.class);
    }
}