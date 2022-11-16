package com.example.diploma.service;

import com.example.diploma.model.Symbol;
import com.example.diploma.repository.SymbolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SymbolService {
    private final SymbolRepository symbolRepository;

    public Symbol create(Symbol symbol) {
        if (symbol.getId() != null) {
            throw new PersistenceException("symbol mustn't contain id");
        }

        return symbolRepository.save(symbol);
    }

    public Symbol update(Symbol symbol) {
        if (!symbolRepository.existsById(symbol.getId())) {
            throw new PersistenceException("symbol with id: " + symbol.getId() + " doesn't exist");
        }

        return symbolRepository.save(symbol);
    }

    public Optional<Symbol> get(Long id) {
        return symbolRepository.findById(id);
    }

    public List<Symbol> findAll(){
        return symbolRepository.findAll();
    }

    public Set<Symbol> findBySymbolIn(Collection<String> symbols){
        return symbolRepository.findBySymbolIn(symbols);
    }

    public void deleteById(long id) {
        symbolRepository.deleteById(id);
    }

    public void deleteAll() {
        symbolRepository.deleteAll();
    }
}
