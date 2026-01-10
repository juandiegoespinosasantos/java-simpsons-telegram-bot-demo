package com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos;

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
 * @version Jan 09, 2026
 * @since 17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpsonsApiPageDTO<R extends SimpsonsApiDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7401455537538678306L;

    private int count;
    private String next;
    private String prev;
    private int pages;
    private List<R> results = new ArrayList<>();
}