package com.example.diploma.configuration.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BinanceProperties {
    @Value("${broker.binance.interval}")
    private String interval;

    @Value("${broker.binance.base-url}")
    private String baseUrl;

    @Value("${broker.binance.symbol-url}")
    private String symbolUrl;

    @Value("${broker.binance.klines-url}")
    private String kLinesUrl;
}
