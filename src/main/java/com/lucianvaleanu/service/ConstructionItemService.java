package com.lucianvaleanu.service;

import com.lucianvaleanu.model.ConstructionItem;
import com.lucianvaleanu.repository.ConstructionItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConstructionItemService {

    private final ConstructionItemRepository repository;

    public ConstructionItemService(ConstructionItemRepository repository) {
        this.repository = repository;
    }

    public List<ConstructionItem> findAll() {
        return repository.findAll();
    }

    public Optional<ConstructionItem> findById(int id) {
        return repository.findById(id);
    }

    public ConstructionItem save(ConstructionItem constructionItem) {
        return repository.save(constructionItem);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(ConstructionItem constructionItem) {
        repository.update(constructionItem);
    }
}