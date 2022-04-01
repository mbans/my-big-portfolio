package com.lumar.portfolio.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lumar.portfolio.domain.Portfolio;
import com.lumar.portfolio.domain.Trade;
import com.lumar.portfolio.service.PortfolioService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller("/portfolios")
@AllArgsConstructor
public class PortfolioController {

    private final PortfolioService service;

    /**
     * Get Portfolio by name
     * @param name
     * @return
     */
    @Get(uri="/{name}")
    public HttpResponse<Portfolio> getPortfoliosByName(final String name) {
        var portfolio = service.getPortfolioByName(name);
        log.info("Returning portfolios {}", portfolio);
        if(portfolio == null) {
            return HttpResponse.noContent();
        }
        else {
            return HttpResponse.ok().body(portfolio);
        }
    }

}
