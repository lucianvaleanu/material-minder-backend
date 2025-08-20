package com.lucianvaleanu.utils.dto;

import java.time.LocalDate;

public record ProjectDTO(
        Integer id,
        String title,
        LocalDate projectDate,
        Integer userId
) {}