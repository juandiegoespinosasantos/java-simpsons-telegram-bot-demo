package com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 09, 2026
 * @since 17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpsonsApiEpisodeDTO implements Serializable, SimpsonsApiDTO {

    @Serial
    private static final long serialVersionUID = -5416868251960054638L;

    @JsonProperty("id")
    private int id;

    @JsonProperty("season")
    private int season;

    @JsonProperty("episode_number")
    private int episodeNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("airdate")
    private String airdate;

    @JsonProperty("synopsis")
    private String synopsis;

    @JsonProperty("image_path")
    private String imagePath;
}