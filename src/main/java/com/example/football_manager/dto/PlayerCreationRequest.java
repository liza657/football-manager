package com.example.football_manager.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

public record PlayerCreationRequest(String firstName, String lastName, String email, String phoneNumber,
                                    LocalDate startOfCareer, LocalDate birthday, UUID teamId) {

    @JsonCreator
    public PlayerCreationRequest(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("phoneNumber") String phoneNumber,
            @JsonProperty("startOfCareer") LocalDate startOfCareer,
            @JsonProperty("birthday") LocalDate birthday,
            @JsonProperty("teamId") UUID teamId) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.startOfCareer = startOfCareer;
        this.birthday = birthday;
        this.teamId = teamId;

    }


}
