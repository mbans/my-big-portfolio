package com.lumar.portfolio.service;

import com.lumar.portfolio.domain.Portfolio;
import com.lumar.portfolio.domain.PortfolioValuation;
import jakarta.inject.Singleton;

import java.util.List;

public interface PortfolioService {
    Portfolio getPortfolioByName(final String name);
    PortfolioValuation getPortfolioValuation(final String name);
}