package com.lumar.portfolio;

import io.micronaut.http.annotation.*;

@Controller("/myBigPortfolio")
public class MyBigPortfolioController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}