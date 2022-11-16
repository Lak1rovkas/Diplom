package com.example.diploma.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "k_lines")
public class KLines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "symbol_id")
    private Symbol symbol;

    @Column(name = "open_time")
    private LocalDateTime openTime;

    @Column
    private double open;

    @Column
    private double high;

    @Column
    private double low;

    @Column
    private double close;

    @Column
    private double volume;

    @Column(name = "close_time")
    private LocalDateTime closeTime;

    @Column(name = "quote_asset_volume")
    private double quoteAssetVolume;

    @Column(name = "number_of_trades")
    private int numberOfTrades;

    @Column(name = "taker_buy_base_asset_volume")
    private double takerBuyBaseAssetVolume;

    @Column(name = "taker_buy_quote_asset_volume")
    private double takerBuyQuoteAssetVolume;

    @Column(name = "can_be_ignored")
    private boolean canBeIgnored;
}
