package com.lucianvaleanu.utils.dto;

import java.time.Instant;

public record UserDTO(
    Integer id,
    String email,
    Instant createdAt
) {}



