package com.example.football_manager.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PlayerDto(String firstName,

                        String lastName,

                        String email,

                        String phoneNumber,

                        LocalDate startOfCareer,

                        LocalDate birthday,

                        UUID teamId) {
}
