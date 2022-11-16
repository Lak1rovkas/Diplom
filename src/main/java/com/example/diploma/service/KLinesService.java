package com.example.diploma.service;

import com.example.diploma.model.KLines;
import com.example.diploma.repository.KLinesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KLinesService {
    private final KLinesRepository kLinesRepository;

    public KLines create(KLines kLines) {
        return kLinesRepository.save(kLines);
    }

    public Optional<KLines> get(Long id) {
        return kLinesRepository.findById(id);
    }

    public void deleteAll() {
        kLinesRepository.deleteAll();
    }

    public void create(List<KLines> kLines) {
        kLinesRepository.saveAll(kLines);
    }
}
