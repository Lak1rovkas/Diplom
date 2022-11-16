package com.example.diploma.clients.binance.dto;

import com.example.diploma.model.Symbol;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KLinesDTO {
    private Long id;
    private Symbol symbolId;
    private Long openTime;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private Long closeTime;
    private double quoteAssetVolume;
    private int numberOfTrades;
    private double takerBuyBaseAssetVolume;
    private double takerBuyQuoteAssetVolume;
    private boolean canBeIgnored;
}
