package com.jdespinosa.simpsons.api.telegram.bot.demo.bots.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author juandiegoespinosasantos@outlook.com
 * @version Jan 09, 2026
 * @since 17
 */
@Getter
@AllArgsConstructor
public enum Commands {

    START("/start"),
    RANDOM_EPISODE("/random_episode");

    private final String command;

    public static Commands valueOfCommand(String command) {
        for (Commands value : values()) {
            if (value.getCommand().equalsIgnoreCase(command)) return value;
        }

        throw new IllegalArgumentException("No enum constant " + Commands.class.getCanonicalName() + "." + command);
    }
}