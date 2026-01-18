package com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 17, 2026
 * @since 17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpsonsApiCharacterDTO implements Serializable, SimpsonsApiDTO {

    @Serial
    private static final long serialVersionUID = 3988302231771491970L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("birthdate")
    private String birthdate;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("status")
    private String status;

    @JsonProperty("description")
    private String description;

    @JsonProperty("first_appearance_ep_id")
    private Integer firstAppearanceEpId;

    @JsonProperty("first_appearance_ep")
    private SimpsonsApiEpisodeDTO firstAppearanceEp;

    @JsonProperty("first_appearance_sh_id")
    private Integer firstAppearanceShId;

    @JsonProperty("first_appearance_sh")
    private SimpsonsApiEpisodeDTO firstAppearanceSh;

    @JsonProperty("portrait_path")
    private String portraitPath;

    @JsonProperty("phrases")
    private List<String> phrases = new ArrayList<>();
}