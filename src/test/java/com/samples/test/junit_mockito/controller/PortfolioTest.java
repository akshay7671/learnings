package com.samples.test.junit_mockito.controller;

import com.samples.test.junit_mockito.model.Stock;
import com.samples.test.junit_mockito.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.AssertionErrors;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PortfolioTest {

    private StockService stockService;
    private Portfolio portfolio;

    @BeforeEach
    void creatMockStockService() {
        portfolio = new Portfolio();
        stockService = mock(StockService.class);
        portfolio.setStockService(stockService);
    }

    @Test
    void getMarketValue() {
        //Creates a list of stocks to be added to the portfolio
        List<Stock> stocks = new ArrayList<Stock>();
        Stock googleStock = new Stock("1", "Google", 10);
        Stock microsoftStock = new Stock("2", "Microsoft", 100);

        stocks.add(microsoftStock);
        stocks.add(googleStock);

        portfolio.setStocks(stocks);

        when(stockService.getPrice(googleStock)).thenReturn(50.0);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.0);

        AssertionErrors.assertTrue("Stock price greater than 10000.0", portfolio.getMarketValue() > 10000);

    }
}