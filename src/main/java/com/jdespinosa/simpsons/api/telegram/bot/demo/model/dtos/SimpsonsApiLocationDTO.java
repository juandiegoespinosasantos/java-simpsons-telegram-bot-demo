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
 * @version Jan 24, 2026
 * @since 17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpsonsApiLocationDTO implements Serializable, SimpsonsApiDTO {

    @Serial
    private static final long serialVersionUID = 4203203434887855797L;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("town")
    private String town;

    @JsonProperty("use")
    private String use;

    @JsonProperty("image_path")
    private String imagePath;
}