package com.lumar.portfolio.service;

import com.lumar.portfolio.domain.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryptoPriceServiceImplTest {

    private CryptoPriceServiceImpl cryptoPriceService = new CryptoPriceServiceImpl();

    @Test
    public void getBitcoinPrice() {
        cryptoPriceService = new CryptoPriceServiceImpl();
        Price btcPrice = cryptoPriceService.getPrice("BTC");
        assertTrue(btcPrice != null);
    }
}
