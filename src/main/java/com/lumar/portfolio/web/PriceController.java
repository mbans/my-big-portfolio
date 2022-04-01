package com.lumar.portfolio.web;


import com.lumar.portfolio.domain.Portfolio;
import com.lumar.portfolio.domain.Price;
import com.lumar.portfolio.service.CryptoPriceServiceImpl;
import com.lumar.portfolio.service.PortfolioService;
import com.lumar.portfolio.service.PriceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller("/prices")
@AllArgsConstructor
public class PriceController {

//    private final PriceService service;

    private final CryptoPriceServiceImpl cryptoPriceService;

//    @Get(uri="/stock/{symbol}")
//    public HttpResponse<Price> getPrice(@PathVariable("symbol") String symbol) {
//        return HttpResponse.ok(this.service.getPrice(symbol));
//    }

    @Get(uri="/crypto/{symbol}")
    public HttpResponse<Price> getCryptoPrice(@PathVariable("symbol") String symbol) {
        return HttpResponse.ok(this.cryptoPriceService.getPrice(symbol));
    }


}
