package com.example.football_manager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Player {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "`first_name`")
    private String firstName;

    @Column(name = "`last_name`")
    private String lastName;

    @Column(name = "`email`")
    private String email;

    @Column(name = "`phone_number`")
    private String phoneNumber;

    @Column(name = "`start_of_career`")
    private LocalDate startOfCareer;

    @Column(name = "`birthday`")
    private LocalDate birthday;


    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Team team;
}
