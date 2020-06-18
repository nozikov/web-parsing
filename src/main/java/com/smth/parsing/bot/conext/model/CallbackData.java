package com.smth.parsing.bot.conext.model;

import com.smth.parsing.bot.handler.Commands;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import reactor.core.publisher.Mono;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CallbackData {

  private Commands command;
  private String dataForCommand;
  private String userId;
  private Integer messageId;
  private static final String DELIMITER = ":";

  public CallbackData(Commands command, String dataForCommand, String userId) {
    this(command, dataForCommand, userId, null);
  }

  /**
   * Create {@link String} for message's callback data.
   *
   * @return {@link String} code
   */
  public String combine() {
    return String.join(DELIMITER, List.of(command.getCommandCode(), dataForCommand, userId));
  }

  /**
   * Create Mono {@link CallbackData} by parsing input String.
   *
   * @param callbackQuery - query with data to parse.
   * @return filled Mono {@link CallbackData}
   */
  public static Mono<CallbackData> parse(CallbackQuery callbackQuery) {
    String callbackQueryString = callbackQuery.getData();
    String[] callbackBlocks = callbackQueryString.split(DELIMITER);
    Integer messageId;
    if (callbackQuery.getMessage() != null) {
      messageId = callbackQuery.getMessage().getMessageId();
    } else {
      messageId = null;
    }
    if (callbackBlocks.length != 3) {
      return Mono.empty();
    }
    return Commands.parse(callbackBlocks[0]).map(
        commandsMono -> new CallbackData(commandsMono, callbackBlocks[1], callbackBlocks[2],
            messageId));
  }

}
