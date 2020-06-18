package com.smth.parsing.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Price")
@Data
public class Price implements Serializable {

  private BigDecimal currentPrice;

  private BigDecimal startPrice;

  private BigDecimal endOfDayPrice;

  private LocalDateTime tradingDay;

}

