package com.graphql.basketballapi.service;

import com.graphql.basketballapi.entity.Player;
import com.graphql.basketballapi.exception.MaxPlayerCountException;
import com.graphql.basketballapi.exception.PlayerAlreadyExistException;
import com.graphql.basketballapi.exception.PlayerNotFoundException;
import com.graphql.basketballapi.model.PlayerModel;
import com.graphql.basketballapi.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerService {

    private final ModelMapper modelMapper;

    private final PlayerRepository playerRepository;

    public PlayerService(
            ModelMapper modelMapper,
            PlayerRepository playerRepository
    ) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    public List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

    public Player addPlayer(PlayerModel playerModel) {
        Long playerCount = playerRepository.count();
        if(playerCount == 5) {
            Map<String, Object> params = new HashMap<>();
            params.put("number", playerCount);
            throw new MaxPlayerCountException("Maximum number of player reached.", params);
        }

        Player existPlayer = playerRepository
                .findByPosition(playerModel.getPosition())
                .orElse(null);
        if(existPlayer != null) {
            Map<String, Object> params = new HashMap<>();
            params.put("position", playerModel.getPosition());
            throw new PlayerAlreadyExistException(
                    "Failed to add player. Player with position already exist.",
                    params
            );
        }

        Player player = modelMapper.map(playerModel, Player.class);
        return playerRepository.save(player);
    }

    public Integer deletePlayer(Integer id) {
        Player player = playerRepository.findById(id).orElse(null);
        if(player == null) {
            throw new PlayerNotFoundException("Player with id: " + id + " not found");
        }

        playerRepository.delete(player);

        return id;
    }
}
