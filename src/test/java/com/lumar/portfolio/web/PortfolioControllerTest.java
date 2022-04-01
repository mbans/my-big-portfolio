package com.lumar.portfolio.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.lumar.portfolio.domain.Portfolio;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Port;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.micronaut.http.HttpRequest.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
public class PortfolioControllerTest {

    @Inject
    @Client("/portfolios")
    HttpClient client;


    @Test
    public void shouldReturnPortfolios() {
        HttpResponse<Portfolio> exchange = client.toBlocking().exchange(GET("/Martin%20Crypto%20Portfolio"), Portfolio.class);

        // When
        System.out.println(exchange);
        // Then


    }



}
