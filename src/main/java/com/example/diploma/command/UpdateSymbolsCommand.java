package com.example.diploma.command;

import com.example.diploma.clients.BrokerClient;
import com.example.diploma.model.Symbol;
import com.example.diploma.service.SymbolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateSymbolsCommand {
    private final BrokerClient brokerClient;
    private final SymbolService symbolService;

    public void update() {
        Set<String> brokerSymbols = brokerClient.getAllSymbols();

        Set<Symbol> dbSymbols = symbolService.findBySymbolIn(brokerSymbols);
        Set<String> existingSymbols = dbSymbols.stream()
                .map(Symbol::getSymbol)
                .collect(Collectors.toSet());
        brokerSymbols.removeAll(existingSymbols);

        for (String symbol : brokerSymbols) {
            Symbol newSymbol = new Symbol(symbol);
            symbolService.create(newSymbol);
        }
    }
}
