package com.example.football_manager.dto;

import java.math.BigDecimal;

public record TeamDto(String name, BigDecimal fee, BigDecimal budget) {
}
