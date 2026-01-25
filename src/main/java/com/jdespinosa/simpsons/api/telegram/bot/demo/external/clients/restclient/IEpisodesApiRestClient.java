package com.jdespinosa.simpsons.api.telegram.bot.demo.external.clients.restclient;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiEpisodeDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 09, 2026
 * @since 17
 */
public interface IEpisodesApiRestClient {

    SimpsonsApiPageDTO<SimpsonsApiEpisodeDTO> findAll(int page);

    SimpsonsApiEpisodeDTO findById(Long id);
}