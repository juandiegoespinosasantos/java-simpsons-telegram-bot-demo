package com.jdespinosa.simpsons.api.telegram.bot.demo.bots;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author juandiegoespinosasantos
 * @version Jan 08, 2026
 * @since 17
 */
@Component
@Slf4j
public class MyFirstBot extends TelegramLongPollingBot {

    private static final String COMMAND_START = "/start";
    private static final String GREETING_MESSAGE = """
            Hello, %s. Welcome to my bot!\n
            This is a demo project to build a simple Telegram Bot.
            The goal is to make use of The Simpsons API (https://thesimpsonsapi.com/) to give it a little bit of functionality...\n
            Stay tuned for more!
            """;

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        Message message = update.getMessage();
        User user = update.getMessage().getFrom();
        log.info("{} wrote '{}'", user.getFirstName(), message.getText());

        processReply(user, message);
    }

    private void processReply(final User user, final Message message) {
        String text = message.getText();
        boolean isStart = message.isCommand() && COMMAND_START.equals(text);
        String reply = isStart ?
                GREETING_MESSAGE.formatted(user.getFirstName()) :
                "You sent: " + text;

        sendMessage(user, reply);
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
}