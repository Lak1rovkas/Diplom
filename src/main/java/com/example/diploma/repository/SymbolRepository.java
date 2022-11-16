package com.example.diploma.repository;

import com.example.diploma.model.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long> {
    List<Symbol> findBySymbol(String symbol);

    Set<Symbol> findBySymbolIn(Collection<String> symbols);

}
