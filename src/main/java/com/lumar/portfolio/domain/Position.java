package com.lumar.portfolio.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.List;

@Jacksonized
@Builder
@Data
public class Position {
    private List<Trade> buyTrades;
    private List<Trade> sellTrade;
}
