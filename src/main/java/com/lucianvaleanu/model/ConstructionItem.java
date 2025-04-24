package com.lucianvaleanu.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "construction_item")
public class ConstructionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "construction_item_id_gen")
    @SequenceGenerator(name = "construction_item_id_gen", sequenceName = "construction_item_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "image", nullable = false, length = Integer.MAX_VALUE)
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}