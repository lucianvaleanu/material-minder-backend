package com.lucianvaleanu.repository;

import com.lucianvaleanu.model.ConstructionItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ConstructionItemRepositoryTest {

    @Autowired
    private ConstructionItemRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void testFindAll() {
        ConstructionItem item1 = new ConstructionItem();
        item1.setName("Brick");
        item1.setPrice(BigDecimal.valueOf(1.50));
        item1.setImage("brick.png");

        ConstructionItem item2 = new ConstructionItem();
        item2.setName("Cement");
        item2.setPrice(BigDecimal.valueOf(5.00));
        item2.setImage("cement.png");

        repository.save(item1);
        repository.save(item2);

        List<ConstructionItem> items = repository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    void testFindById() {
        ConstructionItem item = new ConstructionItem();
        item.setName("Brick");
        item.setPrice(BigDecimal.valueOf(1.50));
        item.setImage("brick.png");

        ConstructionItem savedItem = repository.save(item);
        Optional<ConstructionItem> foundItem = repository.findById(savedItem.getId());

        assertTrue(foundItem.isPresent());
        assertEquals("Brick", foundItem.get().getName());
    }

    @Test
    void testSave() {
        ConstructionItem item = new ConstructionItem();
        item.setName("Brick");
        item.setPrice(BigDecimal.valueOf(1.50));
        item.setImage("brick.png");

        ConstructionItem savedItem = repository.save(item);

        assertNotNull(savedItem.getId());
        assertEquals("Brick", savedItem.getName());
    }

    @Test
    void testDeleteById() {
        ConstructionItem item = new ConstructionItem();
        item.setName("Cement");
        item.setPrice(BigDecimal.valueOf(5.00));
        item.setImage("cement.png");

        ConstructionItem savedItem = repository.save(item);
        repository.deleteById(savedItem.getId());

        Optional<ConstructionItem> foundItem = repository.findById(savedItem.getId());
        assertFalse(foundItem.isPresent());
    }

    @Test
    void testUpdate() {
        ConstructionItem item = new ConstructionItem();
        item.setName("Brick");
        item.setPrice(BigDecimal.valueOf(1.50));
        item.setImage("brick.png");

        ConstructionItem savedItem = repository.save(item);

        savedItem.setName("Updated Brick");
        savedItem.setPrice(BigDecimal.valueOf(2.00));
        repository.update(savedItem);

        Optional<ConstructionItem> updatedItem = repository.findById(savedItem.getId());
        assertTrue(updatedItem.isPresent());
        assertEquals("Updated Brick", updatedItem.get().getName());
        assertEquals(BigDecimal.valueOf(2.00), updatedItem.get().getPrice());
    }
}