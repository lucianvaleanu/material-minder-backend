package com.lucianvaleanu.controller;

import com.lucianvaleanu.model.ConstructionItem;
import com.lucianvaleanu.service.ConstructionItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/construction-items")
public class ConstructionItemController {

    private final ConstructionItemService service;

    public ConstructionItemController(ConstructionItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ConstructionItem>> getAllItems() {
        List<ConstructionItem> items = service.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstructionItem> getItemById(@PathVariable int id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConstructionItem> createItem(@RequestBody ConstructionItem constructionItem) {
        ConstructionItem savedItem = service.save(constructionItem);
        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstructionItem> updateItem(@PathVariable int id, @RequestBody ConstructionItem constructionItem) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        constructionItem.setId(id);
        service.update(constructionItem);
        return ResponseEntity.ok(constructionItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}