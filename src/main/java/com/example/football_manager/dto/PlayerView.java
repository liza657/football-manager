package com.example.football_manager.dto;

import java.time.LocalDate;

public record PlayerView(
        String firstName,

        String lastName,

        String email,

        String phoneNumber,

        LocalDate startOfCareer,

        LocalDate birthday,

        String team){
}
