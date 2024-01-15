package com.example.football_manager.service;

import com.example.football_manager.dto.PlayerCreationRequest;
import com.example.football_manager.dto.PlayerDto;
import com.example.football_manager.dto.PlayerView;
import com.example.football_manager.exception.EntityNotExistsException;
import com.example.football_manager.exception.LackOfFundsException;
import com.example.football_manager.mapper.PlayerMapper;
import com.example.football_manager.model.entity.Player;
import com.example.football_manager.model.entity.Team;
import com.example.football_manager.repository.PlayerRepository;
import com.example.football_manager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {
    private static final String PLAYER_NOT_FOUND = "Player with id:%s not found";
    private static final String TEAM_NOT_FOUND = "Team with id:%s not found";
    private static final String LACK_OF_FUNDS = "Lack of founds";

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    private final TeamRepository teamRepository;

    @Transactional
    @Override
    public Player createPlayer(PlayerCreationRequest playerDto) {
        Player player = playerMapper.dtoToPlayer(playerDto);
        return playerRepository.save(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();

    }

    @Override
    public PlayerView getPlayer(UUID playerId) {
        return playerMapper.playerToView(findPlayerById(playerId));
    }

    @Override
    public void updatePlayer(UUID playerId, PlayerDto playerDto) {
        Player existingPlayer = findPlayerById(playerId);
        playerMapper.updatePlayerFromRequest(playerDto, existingPlayer);
        playerRepository.save(existingPlayer);

    }

    @Override
    public void deletePlayer(UUID playerId) {
        Player player = findPlayerById(playerId);
        playerRepository.delete(player);

    }

    @Override
    public void transferPlayer(UUID playerId, UUID teamId) {
        Player existingPlayer = findPlayerById(playerId);
        Team salesTeam = existingPlayer.getTeam();
        Team buyingTeam = findTeamById(teamId);

        BigDecimal transferAmount = calculateTransfer(playerId);
        BigDecimal transferFee = calculateFee(transferAmount, teamId);
        BigDecimal totalCost = transferAmount.add(transferFee);

        if (!buyingTeam.getPlayers().contains(existingPlayer)) {

            if (buyingTeam.getBudget().compareTo(totalCost) >= 0) {

                existingPlayer.setTeam(buyingTeam);
                salesTeam.setBudget(salesTeam.getBudget().add(totalCost));
                buyingTeam.setBudget(buyingTeam.getBudget().subtract(totalCost));
            } else throw new LackOfFundsException(String.format(LACK_OF_FUNDS));

        }
        teamRepository.save(salesTeam);
        teamRepository.save(buyingTeam);
    }


    private Player findPlayerById(UUID playerId) {
        return playerRepository.findById(playerId).orElseThrow(() ->
                new EntityNotExistsException(String.format(PLAYER_NOT_FOUND, playerId)));
    }

    private Team findTeamById(UUID teamId) {
        return teamRepository.findById(teamId).orElseThrow(() ->
                new EntityNotExistsException(String.format(TEAM_NOT_FOUND, teamId)));
    }

    private long calculateAge(UUID playerId) {
        Player player = findPlayerById(playerId);
        return Period.between(player.getBirthday(), LocalDate.now()).getYears();
    }

    private long calculateExperience(UUID playerId) {
        Player player = findPlayerById(playerId);
        return ChronoUnit.MONTHS.between(player.getStartOfCareer(), LocalDate.now());
    }

    private BigDecimal calculateTransfer(UUID playerId) {
        return BigDecimal.valueOf((double) (calculateExperience(playerId) * 1000) / calculateAge(playerId));
    }

    private BigDecimal calculateFee(BigDecimal sum, UUID teamId) {
        Team team = findTeamById(teamId);
        return sum.multiply(team.getFee().divide(new BigDecimal("100"), RoundingMode.HALF_UP));
    }


}
