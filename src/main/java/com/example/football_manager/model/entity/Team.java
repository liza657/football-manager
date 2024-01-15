package com.example.football_manager.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`team`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`commission`")
    private BigDecimal fee;

    @Column(name = "`budget`")
    private BigDecimal budget;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
