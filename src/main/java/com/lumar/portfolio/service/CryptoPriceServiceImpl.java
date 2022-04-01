package com.lumar.portfolio.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lumar.portfolio.domain.Price;
import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.CachePut;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Retreives prices for crypto assets
 *
 * Uses Coingecko for prices
 */
@Slf4j
@Singleton
@CacheConfig("cryptoprices")
public class CryptoPriceServiceImpl {

    Map<String, Price> cryptoprices = new HashMap();

    @Inject
    InMemoryPriceAliasStore aliasStore;

    public static final String coinGeckoBaseUrl = "https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=usd";

    @Cacheable
    public Price getPrice(final String symbol) {
        try {
            // get the alias
            String alias = aliasStore.get(symbol);

            // request from coin gecko
            String coinGeckoUrl = String.format(coinGeckoBaseUrl, alias);
            var req = HttpRequest.newBuilder(new URI(coinGeckoUrl)).build();
            log.info("Requesting price for {} from {}", symbol, coinGeckoUrl);
            HttpResponse<String> resp = HttpClient
                    .newBuilder()
                    .build()
                    .send(req, HttpResponse.BodyHandlers.ofString());

            // extract price
            BigDecimal usdPrice;
            if(resp.statusCode() == 200) {
                usdPrice = getPrice(alias, resp);
                var price = Price.builder()
                        .price(usdPrice)
                        .date(LocalDate.now())
                        .build();
                return price;
            }

            throw new IllegalArgumentException("Could not retrieve price for " + alias + " from CoinGecko, status=" + resp.statusCode());

        } catch (Exception e) {
            log.info("Could not retrieve Price for {}", symbol);
            throw new IllegalArgumentException("Could not fetch price for " + symbol);
        }
    }

    private BigDecimal getPrice(String alias, HttpResponse<String> resp) throws JsonProcessingException {
        BigDecimal usdPrice;
        ObjectMapper om = new ObjectMapper();
        Map map = om.readValue(resp.body(), Map.class);
        Map usdPriceMap = (Map)map.get(alias);
        usdPrice = new BigDecimal(usdPriceMap.get("usd") + "");
        return usdPrice;
    }
}
