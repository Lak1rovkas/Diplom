package com.example.diploma;

import com.example.diploma.command.UpdateKLinesCommand;
import com.example.diploma.command.UpdateSymbolsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ParserApp {
    private final UpdateSymbolsCommand updateSymbolsCommand;
    private final UpdateKLinesCommand updateKLinesCommand;

    public void run() {
        updateSymbolsCommand.update();

        updateKLinesCommand.update(LocalDate.now());
    }
}
