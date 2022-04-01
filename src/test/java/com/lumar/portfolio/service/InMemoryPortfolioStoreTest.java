package com.lumar.portfolio.service;

import com.lumar.portfolio.domain.Portfolio;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class InMemoryPortfolioStoreTest {

    @Inject
    private InMemoryPortfolioStore store;

    @Test
    public void shouldLoadPortfolio() {
        List<Portfolio> portfolios = store.getPortfolios();

        assertThat(portfolios.size()).isEqualTo(1);
        assertThat(portfolios.get(0).getName()).isEqualTo("Martin Crypto Portfolio");
    }

}
