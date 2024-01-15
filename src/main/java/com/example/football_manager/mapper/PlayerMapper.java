package com.example.football_manager.mapper;

import com.example.football_manager.dto.PlayerCreationRequest;
import com.example.football_manager.dto.PlayerDto;
import com.example.football_manager.dto.PlayerView;
import com.example.football_manager.exception.EntityNotExistsException;
import com.example.football_manager.model.entity.Player;
import com.example.football_manager.model.entity.Team;
import com.example.football_manager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TeamMapper.class})
@RequiredArgsConstructor
public abstract class PlayerMapper {

    protected TeamRepository teamRepository;

    public abstract void updatePlayerFromRequest(PlayerDto request, @MappingTarget Player player);

    public Player dtoToPlayer(PlayerCreationRequest playerDto) {
        Player player = new Player();
        Team team = teamRepository.findById(playerDto.teamId()).orElseThrow(() ->
                new EntityNotExistsException(String.format("Team with id:%s not found", playerDto.teamId())));
        player.setFirstName(playerDto.firstName());
        player.setLastName(playerDto.lastName());
        player.setEmail(playerDto.email());
        player.setBirthday(playerDto.birthday());
        player.setPhoneNumber(playerDto.phoneNumber());
        player.setStartOfCareer(playerDto.startOfCareer());
        player.setTeam(team);
        return player;
    }

    public PlayerView playerToView(Player player) {
        return new PlayerView(
                player.getFirstName(),
                player.getLastName(),
                player.getEmail(),
                player.getPhoneNumber(),
                player.getStartOfCareer(),
                player.getBirthday(),
                player.getTeam().getName()
        );
    }

    @Autowired
    public void setCategoryRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
