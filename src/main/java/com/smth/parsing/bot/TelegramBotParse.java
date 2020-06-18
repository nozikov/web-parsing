package com.smth.parsing.bot;

import com.smth.parsing.bot.handler.UpdateHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBotParse extends TelegramLongPollingBot {

  @Getter
  private final String botUsername;

  @Getter
  private final String botToken;

  private final UpdateHandler handler;


  private static DefaultBotOptions defaultBotOption(TelegramBotProperties telegramBotProperties) {
    DefaultBotOptions options = new DefaultBotOptions();
    options.setBaseUrl(telegramBotProperties.getBaseUrl());
    return options;
  }

  /**
   * Constructor with autowired parameters from configuration.
   */

  public TelegramBotParse(UpdateHandler updateHandler,
                          TelegramBotProperties telegramBotProperties) {
    super(defaultBotOption(telegramBotProperties));

    handler = updateHandler;
    botUsername = telegramBotProperties.getUserName();
    botToken = telegramBotProperties.getToken();
  }

  @Override
  public void onUpdateReceived(Update update) {
    handler.handle(update).subscribe(this::sendMessageOrCallback);
  }

  private <T extends BotApiMethod> void sendMessageOrCallback(T method) {
    try {
      execute(method);
    } catch (TelegramApiException e) {
      log.warn("Sending failed!", e);
    }
  }

}
