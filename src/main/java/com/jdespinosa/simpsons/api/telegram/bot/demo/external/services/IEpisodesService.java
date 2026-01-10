package com.jdespinosa.simpsons.api.telegram.bot.demo.external.services;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiEpisodeDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiEpisodesPageDTO;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 09, 2026
 * @since 17
 */
public interface IEpisodesService {

    SimpsonsApiEpisodesPageDTO findAll(int page);

    SimpsonsApiEpisodeDTO findById(Long id);
}