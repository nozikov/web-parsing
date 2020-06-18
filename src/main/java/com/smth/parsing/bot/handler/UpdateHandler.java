package com.smth.parsing.bot.handler;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class UpdateHandler {

  private CallbackParser callbackParser;

  public Flux<? extends BotApiMethod> handle(Update update) {
    return callbackParser.parse(update);
  }

  private Message getMessageFromUpdate(Update update) {
    return Optional.ofNullable(update.getMessage())
        .orElseGet(() -> update.getCallbackQuery().getMessage());
  }
}
