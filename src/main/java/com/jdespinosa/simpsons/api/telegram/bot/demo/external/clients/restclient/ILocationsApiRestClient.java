package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiLocationDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 24, 2026
 * @since 17
 */
public interface ILocationsApiRestClient {

    SimpsonsApiPageDTO<SimpsonsApiLocationDTO> findAll(int page);

    SimpsonsApiLocationDTO findById(Long id);
}