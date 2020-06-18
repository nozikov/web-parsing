package com.smth.parsing.bot.handler;

import com.smth.parsing.bot.conext.model.CallbackData;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Flux;

@Component
public class CallbackParser {

  private final Map<Commands, CallbackHandler> map;

  /**
   * Constructor for creating map where key is command and value is command's handler.
   */
  @Autowired
  public CallbackParser(List<CallbackHandler> list) {
    this.map = list.stream()
        .collect(Collectors.toMap(CallbackHandler::getCommand, Function.identity()));
  }

  /**
   * Parse callbackQuery's data from Update and return answer.
   *
   * @param update - update from user
   * @return message for Telegram Bot Api
   */
  public Flux<BotApiMethod> parse(Update update) {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    return CallbackData.parse(callbackQuery)
        .filter(callbackData -> map.containsKey(callbackData.getCommand()))
        .flux()
        .flatMap(callbackData -> map.get(callbackData.getCommand())
            .handle(callbackData, callbackQuery.getId()));

  }

}
