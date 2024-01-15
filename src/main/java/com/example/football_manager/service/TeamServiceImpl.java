package com.example.football_manager.service;

import com.example.football_manager.dto.TeamDto;
import com.example.football_manager.exception.EntityNotExistsException;
import com.example.football_manager.mapper.TeamMapper;
import com.example.football_manager.model.entity.Team;
import com.example.football_manager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private static final String TEAM_NOT_FOUND = "Team with id:%s not found";

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team createTeam(TeamDto teamDto) {
        Team team = teamMapper.dtoToTeam(teamDto);
        return teamRepository.save(team);
    }

    @Override
    public TeamDto getTeam(UUID teamId) {
        return teamMapper.teamToDto(findTeamById(teamId));
    }

    @Override
    public void updateTeam(UUID teamId, TeamDto teamDto) {
        Team existingTeam = findTeamById(teamId);
        teamMapper.updateTeamFromRequest(teamDto, existingTeam);
        teamRepository.save(existingTeam);
    }

    @Override
    public void deleteTeam(UUID teamId) {
        teamRepository.delete(findTeamById(teamId));
    }


    private Team findTeamById(UUID teamId) {
        return teamRepository.findById(teamId).orElseThrow(() ->
                new EntityNotExistsException(String.format(TEAM_NOT_FOUND, teamId)));
    }
}
