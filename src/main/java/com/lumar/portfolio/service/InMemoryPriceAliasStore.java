package com.lumar.portfolio.service;

import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

//TODO: This should be in the database
@Singleton
public class InMemoryPriceAliasStore {

    Map<String, String> aliases = new HashMap<>();

    InMemoryPriceAliasStore() {
        add("btc", "bitcoin");
        add("dot", "polkadot");
        add("dot", "polkadot");
        add("cro", "cronos");
        add("usd-coin", "usd-coin");
    }

    public String get(final String symbol) {
        return aliases.get(symbol) == null ? symbol : aliases.get(symbol);
    }

    private void add(String symbol, String alias) {
        this.aliases.put(symbol, alias);
    }
}
