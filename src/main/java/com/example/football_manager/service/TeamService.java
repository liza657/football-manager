package com.example.football_manager.service;

import com.example.football_manager.dto.TeamDto;
import com.example.football_manager.model.entity.Team;

import java.util.List;
import java.util.UUID;

public interface TeamService {

    List<Team> getAllTeams();

    Team createTeam(TeamDto teamDto);

    TeamDto getTeam(UUID teamId);

    void updateTeam(UUID teamId, TeamDto teamDto);

    void deleteTeam(UUID teamId);

}
