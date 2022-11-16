package com.example.diploma.repository;

import com.example.diploma.model.KLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KLinesRepository extends JpaRepository<KLines, Long> {
}
