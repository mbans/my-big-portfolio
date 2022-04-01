package com.lumar.portfolio.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lumar.portfolio.domain.Portfolio;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Singleton
@Getter
@AllArgsConstructor
public class InMemoryPortfolioStore {

    private final List<Portfolio> portfolios;

    public Portfolio findPortfoliosByName(String name) {
        return portfolios.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @SneakyThrows
    @PostConstruct
    public void populate() {
        Portfolio portfolio = loadPortfolioFromJson();
        log.info("Loaded Portfolio with {} trades", portfolio.getTrades().size());
        this.portfolios.add(portfolio);
        log.info("Added portfolio", portfolio.getName());
    }

    private Portfolio loadPortfolioFromJson() throws URISyntaxException, IOException {
        var portfolioFile = Paths.get(
                this.getClass().
                getResource("/trade-portfolio.json").
                toURI()).toFile();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        Portfolio portfolio = om.readValue(portfolioFile, Portfolio.class);
        return portfolio;
    }
}
