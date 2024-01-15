package com.example.football_manager.controller;

import com.example.football_manager.dto.PlayerCreationRequest;
import com.example.football_manager.dto.PlayerDto;
import com.example.football_manager.dto.PlayerView;
import com.example.football_manager.model.entity.Player;
import com.example.football_manager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/players")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/getPlayer/{userId}")
    public ResponseEntity<PlayerView> getPlayerById(@PathVariable("userId") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                playerService.getPlayer(id));
    }

    @GetMapping("/getPLayers")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.status(HttpStatus.OK).body(
                playerService.getAllPlayers());
    }

    @PostMapping("/createPlayer")
    public ResponseEntity<Player> createPlayer(@RequestBody PlayerCreationRequest request) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                playerService.createPlayer(request));
    }

    @PutMapping("/updatePlayer/{playerId}")
    public void updatePlayer(@PathVariable("playerId") UUID id,
                             @RequestBody PlayerDto request) {
        playerService.updatePlayer(id, request);
    }

    @DeleteMapping("/deletePlayer/{playerId}")
    public void deletePlayer(@PathVariable("playerId") UUID id) {
        playerService.deletePlayer(id);
    }

    @PostMapping("/transferPlayer")
    public void transferPlayer(@RequestParam UUID playerId, @RequestParam UUID teamId) {
        playerService.transferPlayer(playerId, teamId);
    }

}
