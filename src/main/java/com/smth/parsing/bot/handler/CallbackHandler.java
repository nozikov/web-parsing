package com.smth.parsing.bot.handler;

import com.smth.parsing.bot.conext.model.CallbackData;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import reactor.core.publisher.Flux;

public interface CallbackHandler {

  Flux<? extends BotApiMethod> handle(CallbackData callbackData, String callbackQueryId);

  Commands getCommand();

  /**
   * Create {@link AnswerCallbackQuery} with text from input.
   *
   * @param text - message
   * @param callbackQueryId - id of callback
   * @return {@link AnswerCallbackQuery} for TelegramBot API
   */
  static AnswerCallbackQuery createAnswerCallback(String text, String callbackQueryId) {
    return new AnswerCallbackQuery()
        .setText(text)
        .setCallbackQueryId(callbackQueryId);
  }
}
