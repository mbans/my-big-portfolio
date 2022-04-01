package com.lumar.portfolio.domain;

import io.micronaut.http.annotation.Get;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@Jacksonized
public class Price {
    private BigDecimal price;
    private LocalDate date;
}
