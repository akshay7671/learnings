package com.samples.test.junit_mockito.service;

import com.samples.test.junit_mockito.model.Stock;

public interface StockService {
    double getPrice(Stock stock);
}