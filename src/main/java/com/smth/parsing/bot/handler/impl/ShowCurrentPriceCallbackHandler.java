package com.smth.parsing.bot.handler.impl;

import com.smth.parsing.bot.conext.model.CallbackData;
import com.smth.parsing.bot.handler.CallbackHandler;
import com.smth.parsing.bot.handler.Commands;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import reactor.core.publisher.Flux;

@Component
public class ShowCurrentPriceCallbackHandler implements CallbackHandler {

  @Override
  public Flux<? extends BotApiMethod> handle(CallbackData callbackData, String callbackQueryId) {
    return null;
  }

  @Override
  public Commands getCommand() {
    return null;
  }
}
