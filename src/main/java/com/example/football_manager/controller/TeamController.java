package com.example.football_manager.controller;

import com.example.football_manager.dto.TeamDto;
import com.example.football_manager.model.entity.Team;
import com.example.football_manager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teams")
public class TeamController {
    private final TeamService teamService;


    @GetMapping("/getTeam/{teamId}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable("teamId") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                teamService.getTeam(id));
    }

    @GetMapping("/getTeams")
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.status(HttpStatus.OK).body(
                teamService.getAllTeams());
    }

    @PostMapping("/createTeam")
    public ResponseEntity<Team> createTeam(@RequestBody TeamDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                teamService.createTeam(request));
    }

    @PutMapping("/updateTeam/{teamId}")
    public void updateTeam(@PathVariable("teamId") UUID id,
                           @RequestBody TeamDto request) {
        teamService.updateTeam(id, request);
    }


    @DeleteMapping("/deleteTeam/{teamId}")
    public void deleteTeam(@PathVariable("teamId") UUID id) {
        teamService.deleteTeam(id);
    }
}
