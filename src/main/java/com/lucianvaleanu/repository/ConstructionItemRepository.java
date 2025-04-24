package com.lucianvaleanu.repository;

import com.lucianvaleanu.model.ConstructionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConstructionItemRepository extends JpaRepository<ConstructionItem, Integer> {
    List<ConstructionItem> findAll();
    Optional<ConstructionItem> findById(int id);
    ConstructionItem save(ConstructionItem constructionItem);
    void deleteById(int id);

    @Modifying
    @Query("UPDATE ConstructionItem c SET c.name = :#{#constructionItem.name}, c.price = :#{#constructionItem.price} WHERE c.id = :#{#constructionItem.id}")
    void update(@Param("constructionItem") ConstructionItem constructionItem);

}
