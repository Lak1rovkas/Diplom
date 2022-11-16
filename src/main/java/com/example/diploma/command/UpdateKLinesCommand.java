package com.example.diploma.command;

import com.example.diploma.clients.BrokerClient;
import com.example.diploma.model.KLines;
import com.example.diploma.model.Symbol;
import com.example.diploma.repository.KLinesRepository;
import com.example.diploma.repository.SymbolRepository;
import com.example.diploma.service.KLinesService;
import com.example.diploma.service.SymbolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UpdateKLinesCommand {
    private static final LocalDate MIN_START_DATE = LocalDate.of(2015, 1, 1);
    private final BrokerClient brokerClient;
    private final KLinesService kLinesService;
    private final SymbolService symbolService;

    public void update(LocalDate untilDate) {
        List<Symbol> symbols = symbolService.findAll();
        for (Symbol symbol : symbols) {
            if (symbol.getLastUpdate() == null || untilDate.isAfter(symbol.getLastUpdate())) {
                LocalDate minDate = symbol.getLastUpdate() == null
                        ? MIN_START_DATE
                        : symbol.getLastUpdate();
                for (LocalDate startDate = minDate; startDate.isBefore(LocalDate.now()); startDate = startDate.plusDays(1)) {
                    LocalDate endDate = startDate.plusDays(1);
                    List<KLines> kLines = brokerClient.getKLines(symbol, startDate, startDate.plusDays(1));
                    kLinesService.create(kLines);
                    symbol.setLastUpdate(endDate);
                    symbolService.update(symbol);
                }
            }
        }
    }
}
