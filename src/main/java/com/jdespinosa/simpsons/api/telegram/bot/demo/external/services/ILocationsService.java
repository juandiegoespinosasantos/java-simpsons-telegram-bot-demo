package com.jdespinosa.simpsons.api.telegram.bot.demo.external.services;

import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiLocationDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiPageDTO;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 24, 2026
 * @since 17
 */
public interface ILocationsService {

    SimpsonsApiPageDTO<SimpsonsApiLocationDTO> findAll(int page);

    SimpsonsApiLocationDTO findById(Long id);
}