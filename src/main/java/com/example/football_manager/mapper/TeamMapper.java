package com.example.football_manager.mapper;

import com.example.football_manager.dto.TeamDto;
import com.example.football_manager.model.entity.Team;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@RequiredArgsConstructor

public abstract class TeamMapper {

    public abstract TeamDto teamToDto(Team team);

    public abstract Team dtoToTeam(TeamDto teamDto);

    public abstract void updateTeamFromRequest(TeamDto request, @MappingTarget Team team);

} 
