package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiCharacterDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 17, 2026
 * @since 17
 */
public interface ICharactersApiRestClient {

    SimpsonsApiPageDTO<SimpsonsApiCharacterDTO> findAll(int page);

    SimpsonsApiCharacterDTO findById(Long id);
}