package com.lucianvaleanu.utils.dto;

import java.math.BigDecimal;

public record ConstructionItemDTO(
        Integer id,
        String name,
        BigDecimal price,
        String image
) {}