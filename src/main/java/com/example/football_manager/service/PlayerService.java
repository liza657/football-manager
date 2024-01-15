package com.example.football_manager.service;

import com.example.football_manager.dto.PlayerCreationRequest;
import com.example.football_manager.dto.PlayerDto;
import com.example.football_manager.dto.PlayerView;
import com.example.football_manager.dto.TeamDto;
import com.example.football_manager.model.entity.Player;
import com.example.football_manager.model.entity.Team;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PlayerService {

    Player createPlayer (PlayerCreationRequest PlayerCreationRequest) throws IOException;

    List<Player> getAllPlayers();

    PlayerView getPlayer(UUID playerId);

    void updatePlayer(UUID playerId, PlayerDto playerDto);

    void deletePlayer(UUID playerId);

    void transferPlayer(UUID playerId, UUID teamId);


}
