package com.jdespinosa.simpsons.api.telegram.bot.demo.bots;

import com.jdespinosa.simpsons.api.telegram.bot.demo.bots.enums.Commands;
import com.jdespinosa.simpsons.api.telegram.bot.demo.external.services.ICharactersService;
import com.jdespinosa.simpsons.api.telegram.bot.demo.external.services.IEpisodesService;
import com.jdespinosa.simpsons.api.telegram.bot.demo.external.services.ILocationsService;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiCharacterDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiEpisodeDTO;
import com.jdespinosa.simpsons.api.telegram.bot.demo.model.dtos.SimpsonsApiLocationDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;
import java.util.Random;

/**
 * @author juandiegoespinosasantos
 * @version Jan 08, 2026
 * @since 17
 */
@Component
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
    private static final int MAX_CHARACTERS = 1182;
    private static final int MAX_LOCATIONS = 477;
    private static final int MAX_DESCRIPTION_SIZE = 250;
    private static final int IMG_SIZE_EPISODE = 500;
    private static final int IMG_SIZE_CHARACTER = 200;

    private final IEpisodesService episodesService;
    private final ICharactersService charactersService;
    private final ILocationsService locationsService;

    @Value("${telegram.bot.username}")
    private String telegramBotUsername;

    @Value("${telegram.bot.token}")
    private String telegramBotToken;

    @Value("${simpsons.cdn.base-url}")
    private String simpsonsCdnBaseUrl;

    @Autowired
    public MyFirstBot(IEpisodesService episodesService,
                      ICharactersService charactersService,
                      ILocationsService locationsService) {
        this.episodesService = episodesService;
        this.charactersService = charactersService;
        this.locationsService = locationsService;
    }

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
            log.error("D'oh! Something went wrong.", ex);
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
            log.error("D'oh! Something went wrong.", ex);
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
                case RANDOM_CHARACTER -> processRandomCharacter(user);
                case RANDOM_LOCATION -> processRandomLocation(user);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            sendMessage(user, "D'oh! Something went wrong.");
        }
    }

    private void processStart(final User user) {
        String reply = GREETING_MESSAGE.formatted(user.getFirstName());
        sendMessage(user, reply);
    }

    private void processRandomEpisode(final User user) {
        long randomId = getRandomNumber(MAX_EPISODES);

        SimpsonsApiEpisodeDTO episode = episodesService.findById(randomId);
        String imageUrl = simpsonsCdnBaseUrl + '/' + IMG_SIZE_EPISODE + episode.getImagePath();
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
            log.error(ex.getMessage(), ex);
            sendMessage(user, "D'oh! Something went wrong.");
        }
    }

    private void processRandomCharacter(final User user) {
        long randomId = getRandomNumber(MAX_CHARACTERS);

        SimpsonsApiCharacterDTO character = charactersService.findById(randomId);
        String imageUrl = simpsonsCdnBaseUrl + '/' + IMG_SIZE_CHARACTER + character.getPortraitPath();
        String caption = buildCharacterCaption(character);

        try {
            sendPhoto(user, imageUrl, caption);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            sendMessage(user, "D'oh! Something went wrong.");
        }
    }

    private void processRandomLocation(final User user) {
        long randomId = getRandomNumber(MAX_LOCATIONS);

        SimpsonsApiLocationDTO location = locationsService.findById(randomId);
        String imageUrl = simpsonsCdnBaseUrl + '/' + IMG_SIZE_CHARACTER + location.getImagePath();
        String caption = buildLocationCaption(location);

        try {
            sendPhoto(user, imageUrl, caption);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            sendMessage(user, "D'oh! Something went wrong.");
        }
    }

    private String buildCharacterCaption(final SimpsonsApiCharacterDTO character) {
        StringBuilder sb = new StringBuilder()
                .append("#")
                .append(character.getId())
                .append("/")
                .append(MAX_CHARACTERS)
                .append(" - ")
                .append(character.getName());

        Integer age = character.getAge();
        if (age != null) sb.append("\n").append("Age: ").append(age);

        String occupation = character.getOccupation();
        if (StringUtils.isNotEmpty(occupation)) sb.append("\n").append(occupation);

        String phrase = getRandomCharacterPhrase(character.getPhrases());
        if (StringUtils.isNotEmpty(phrase)) sb.append("\n\n\"").append(phrase).append("\"");

        String description = getCharacterDescription(character.getDescription());
        if (StringUtils.isNotEmpty(description)) sb.append("\n\n").append(description);

        return sb.toString();
    }

    private String getCharacterDescription(final String description) {
        if (description.length() <= MAX_DESCRIPTION_SIZE) return description;

        String substring = description.substring(0, (MAX_DESCRIPTION_SIZE - 3)).trim();

        return substring + "...";
    }

    private String getRandomCharacterPhrase(final List<String> phrases) {
        if (phrases.isEmpty()) return "";

        int idx = getRandomNumber(phrases.size()) - 1;

        return phrases.get(idx);
    }

    private String buildLocationCaption(final SimpsonsApiLocationDTO location) {
        StringBuilder sb = new StringBuilder()
                .append("#")
                .append(location.getId())
                .append("/")
                .append(MAX_LOCATIONS)
                .append(" - ")
                .append(location.getName());

        String town = location.getTown();
        if (StringUtils.isNotEmpty(town)) sb.append("\n\n").append(town);

        String use = location.getUse();
        if (StringUtils.isNotEmpty(use)) sb.append("\n").append(use);

        return sb.toString();
    }

    private int getRandomNumber(final int limit) {
        return new Random().nextInt(limit) + 1;
    }
}