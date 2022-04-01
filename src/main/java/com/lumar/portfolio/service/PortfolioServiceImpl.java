package com.lumar.portfolio.service;

import com.lumar.portfolio.domain.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Singleton
public class PortfolioServiceImpl implements PortfolioService {

    private final InMemoryPortfolioStore store;

    @Inject
    private CryptoPriceServiceImpl cryptoPriceService;


    @Override
    public Portfolio getPortfolioByName(String name) {
        return store.findPortfoliosByName(name);
    }

    @Override
    public PortfolioValuation getPortfolioValuation(final String name) {
        var now = LocalDateTime.now();
        var portfolio = this.getPortfolioByName(name);
        if(portfolio == null) {
            return null;
        }

        portfolio.getTrades().stream()
                .map(t -> createTradeValuation(t, now))
                .collect(Collectors.toList());

    }

    private TradeValuation createTradeValuation(Trade t, LocalDateTime now) {
        var currentPrice = getPrice(t);

        TradeValuation.builder()
                .trade(t)
                .tradeValuationDateTime(now)
                .valuation()
                .build();
    }

    private Price getPrice(Trade t) {
        Price price = null;
        if(t.getAsset().toLowerCase().equals("crypto")) {
            price = cryptoPriceService.getPrice(t.getSymbol());
            if(price == null) {
                log.info("Could not find price for {}");
                return Price.builder().price(BigDecimal.ZERO).date(LocalDate.now()).build();
            }
        }
        return price;
    }

}
