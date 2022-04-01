package com.lumar.portfolio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The individual PnL associated with each trade
 */
@Jacksonized
@Builder
@JsonIgnoreProperties(value = {"quantity"}, ignoreUnknown = true)
@ToString
@Data
@Introspected
public class TradeValuation {
    private Trade trade;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tradeValuationDateTime;
    private BigDecimal valuation;
    private BigDecimal pnlAmount;
}
