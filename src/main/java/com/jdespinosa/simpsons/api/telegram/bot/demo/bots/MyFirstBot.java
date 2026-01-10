package com.jdespinosa.simpsons.api.telegram.bot.demo.bots;

import com.jdespinosa.simpsons.api.telegram.bot.demo.bots.enums.Commands;
import com.jdespinosa.simpsons.api.telegram.bot.demo.external.services.IEpisodesService;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiEpisodeDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

/**
 * @author juandiegoespinosasantos
 * @version Jan 08, 2026
 * @since 17
 */
@Component
@AllArgsConstructor
@Slf4j
public class MyFirstBot extends TelegramLongPollingBot {

    private static final String GREETING_MESSAGE = """
            Hello, %s. Welcome to my bot!\n
            This is a demo project to build a simple Telegram Bot.
            The goal is to make use of The Simpsons API (https://thesimpsonsapi.com/) to give it a little bit of functionality...\n
            Stay tuned for more!
            """;
    private static final String DEFAULT_REPLY = "You sent %s";
    private static final int MAX_EPISODES = 768;
    private static final int DEFAULT_IMG_SIZE = 500;

    private final IEpisodesService episodesService;

    @Value("${telegram.bot.username}")
    private String telegramBotUsername;

    @Value("${telegram.bot.token}")
    private String telegramBotToken;

    @Value("${simpsons.cdn.base-url}")
    private String simpsonsCdnBaseUrl;

    @Override
    public String getBotUsername() {
        return telegramBotUsername;
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        Message message = update.getMessage();
        User user = update.getMessage().getFrom();
        String text = message.getText();
        log.info("{} wrote '{}'", user.getFirstName(), message.getText());

        if (message.isCommand()) {
            processCommandResponse(user, text);
        } else {
            processDefaultReply(user, text);
        }
    }

    private void sendMessage(final User destination, final String message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(destination.getId().toString())
                .text(message)
                .build();

        try {
            execute(sendMessage);
        } catch (TelegramApiException ex) {
            log.error("Something went horribly wrong!", ex);
            throw new RuntimeException(ex);
        }
    }

    private void sendPhoto(final User destination, final String imageUrl, final String caption) {
        InputFile photo = new InputFile(imageUrl);
        SendPhoto sendPhoto = SendPhoto.builder()
                .chatId(destination.getId().toString())
                .photo(photo)
                .caption(caption)
                .build();

        try {
            execute(sendPhoto);
        } catch (TelegramApiException ex) {
            log.error("Something went horribly wrong!", ex);
            throw new RuntimeException(ex);
        }
    }

    private void processDefaultReply(final User user, final String text) {
        String reply = DEFAULT_REPLY.formatted(text);
        sendMessage(user, reply);
    }

    private void processCommandResponse(final User user, final String text) {
        try {
            Commands command = Commands.valueOfCommand(text);

            switch (command) {
                case START -> processStart(user);
                case RANDOM_EPISODE -> processRandomEpisode(user);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            processDefaultReply(user, text);
        }
    }

    private void processStart(final User user) {
        String reply = GREETING_MESSAGE.formatted(user.getFirstName());
        sendMessage(user, reply);
    }

    private void processRandomEpisode(final User user) {
        long randomNumber = new Random().nextInt(MAX_EPISODES) + 1;

        SimpsonsApiEpisodeDTO episode = episodesService.findById(randomNumber);
        String imageUrl = simpsonsCdnBaseUrl + '/' + DEFAULT_IMG_SIZE + episode.getImagePath();
        String caption = ("""                
                #%d - %s
                Season: %d
                Original aired on %s
                \"%s\"
                """).formatted(episode.getId(), episode.getName(), episode.getSeason(), episode.getAirdate(),
                episode.getSynopsis());

        try {
            sendPhoto(user, imageUrl, caption);
        } catch (Exception ex) {
            sendMessage(user, "Something went wrong!");
        }
    }
}