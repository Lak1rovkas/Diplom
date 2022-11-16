package com.example.diploma.clients;

import com.example.diploma.model.KLines;
import com.example.diploma.model.Symbol;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BrokerClient {
    Set<String> getAllSymbols();

    List<KLines> getKLines(Symbol symbol, LocalDate startDate, LocalDate endDate);
}
