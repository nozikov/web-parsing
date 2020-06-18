package com.smth.parsing.bot.handler;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.core.publisher.Mono;

@Getter
@AllArgsConstructor
public enum Commands {
  CHOICE_CURRENCY("CH_CUR"),
  SHOW_CURRENT_PRICE("CURRENT_PRICE");

  private final String commandCode;
  private static final Map<String, Commands> map;

  static {
    map = Arrays.stream(Commands.values())
        .collect(Collectors.toMap(Commands::getCommandCode, command -> command));
  }

  /**
   * Return Mono {@link Commands} by parsing input String.
   *
   * @param commandCode - input String.
   * @return Mono {@link Commands}
   */
  public static Mono<Commands> parse(String commandCode) {
    return Mono.justOrEmpty(map.get(commandCode));
  }
}
