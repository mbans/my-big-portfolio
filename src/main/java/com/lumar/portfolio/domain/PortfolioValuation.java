package com.lumar.portfolio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a portfolio that is valued.
 */
@Jacksonized
@Builder
@Data
@Introspected
public class PortfolioValuation {

    private Long portfolioId;
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime valuationDateTime;

    private List<Trade> trades;
}
