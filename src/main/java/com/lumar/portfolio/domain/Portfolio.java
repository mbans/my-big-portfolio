package com.lumar.portfolio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.List;

@Jacksonized
@Builder
@Data
@Introspected
public class Portfolio {
    private Long id;
    private String owner;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate creationDate;
    private String name;
    private List<Trade> trades;
}
