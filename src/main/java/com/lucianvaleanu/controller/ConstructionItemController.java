package com.lucianvaleanu.controller;

import com.lucianvaleanu.model.ConstructionItem;
import com.lucianvaleanu.service.ConstructionItemService;
import com.lucianvaleanu.utils.dto.ConstructionItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/construction-items")
public class ConstructionItemController {

    private final ConstructionItemService service;

    public ConstructionItemController(ConstructionItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ConstructionItemDTO>> getAllItems() {
        List<ConstructionItemDTO> items = service.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstructionItemDTO> getItemById(@PathVariable int id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConstructionItemDTO> createItem(@RequestBody ConstructionItemDTO constructionItemDTO) {
        ConstructionItem savedItem = service.save(toEntity(constructionItemDTO));
        return ResponseEntity.ok(toDTO(savedItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstructionItemDTO> updateItem(@PathVariable int id, @RequestBody ConstructionItemDTO constructionItemDTO) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ConstructionItem entity = toEntity(constructionItemDTO);
        entity.setId(id);
        service.update(entity);
        return ResponseEntity.ok(toDTO(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private ConstructionItemDTO toDTO(ConstructionItem item) {
        return new ConstructionItemDTO(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getImage()
        );
    }

    private ConstructionItem toEntity(ConstructionItemDTO dto) {
        ConstructionItem item = new ConstructionItem();
        item.setId(dto.id());
        item.setName(dto.name());
        item.setPrice(dto.price());
        item.setImage(dto.image());
        return item;
    }
}