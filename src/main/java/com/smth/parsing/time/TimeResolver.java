package com.smth.parsing.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

@Component
public class TimeResolver {
  private static final ZoneId UTC = ZoneId.of("UTC");
  private static final ZoneId MSK = ZoneId.of("Europe/Moscow");

  public LocalDateTime convertToUtc(LocalDateTime time) {
    return convert(time, MSK, UTC);
  }

  public LocalDateTime convertFromUtc(LocalDateTime time) {
    return convert(time, UTC, MSK);
  }

  public LocalDateTime getCurrentServerTime() {
    return LocalDateTime.now(UTC);
  }

  private LocalDateTime convert(LocalDateTime source, ZoneId from, ZoneId to) {
    return source.atZone(from)
        .withZoneSameInstant(to)
        .toLocalDateTime();
  }
}
