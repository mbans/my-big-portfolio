package com.lumar.portfolio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Jacksonized
@Builder
@ToString
@Data
@Introspected
public class Trade {

    private String symbol;

// TODO: Fix put back to LocalDate
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate tradeDate;
    private String broker;
    private String asset;
    private double quantity;
    private String ccy;

    // price of stock at time of purchase
    private BigDecimal unitPrice;

    // price of tx
    private BigDecimal tradePrice;
}
