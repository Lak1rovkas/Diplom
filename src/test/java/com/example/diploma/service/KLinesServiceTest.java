package com.example.diploma.service;

import com.example.diploma.model.KLines;
import com.example.diploma.model.Symbol;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class KLinesServiceTest {
    @Autowired
    private KLinesService kLinesService;
    @Autowired
    private SymbolService symbolService;

    private Symbol symbol;

    @BeforeAll
    public void setup() {
        Symbol symbol = new Symbol();
        symbol.setSymbol("TEST");
        this.symbol = symbolService.create(symbol);
    }

    @Test
    public void create_shouldCreateKLines() {
        KLines kLines = new KLines();
        kLines.setSymbol(symbol);
        kLines.setOpenTime(LocalDateTime.now());
        kLines.setCloseTime(LocalDateTime.now());
        kLines.setOpen(54.23543534);
        kLines.setClose(87.28768769);

        kLines = kLinesService.create(kLines);

        Optional<KLines> result = kLinesService.get(kLines.getId());

        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getOpen()).isEqualTo(kLines.getOpen());
        assertThat(result.get().getClose()).isEqualTo(kLines.getClose());
    }

    @AfterAll
    public void cleanup() {
        symbolService.deleteById(symbol.getId());
        kLinesService.deleteAll();
    }
}
