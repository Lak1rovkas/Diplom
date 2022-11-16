package com.example.diploma.service;

import com.example.diploma.model.Symbol;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SymbolServiceTest {
    @Autowired
    private SymbolService service;

    @Test
    public void create_shouldCreateSymbol() {
        Symbol symbol = new Symbol();
        symbol.setSymbol("TEST");

        symbol = service.create(symbol);

        Optional<Symbol> result = service.get(symbol.getId());

        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getSymbol()).isEqualTo(symbol.getSymbol());
    }

    @AfterAll
    public void cleanup() {
        service.deleteAll();
    }
}
