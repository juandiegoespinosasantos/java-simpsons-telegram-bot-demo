package com.jdespinosa.simpsons.api.telegram.bot.demo.bots.config;

import com.jdespinosa.simpsons.api.telegram.bot.demo.bots.MyFirstBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @author juandiegoespinosasantos
 * @version Jan 08, 2026
 * @since 17
 */
@Configuration
public class TelegramConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(MyFirstBot myFirstBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(myFirstBot);

        return botsApi;
    }
}