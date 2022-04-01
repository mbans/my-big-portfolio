package com.lumar.portfolio.service;

import com.lumar.portfolio.domain.Asset;
import com.lumar.portfolio.domain.Price;

public interface PriceService {

    Price getPrice(final String stock, final Asset asset);

    Price getPrice(final String stock);

}
