package com.graphql.basketballapi.service;

import com.graphql.basketballapi.entity.Player;
import com.graphql.basketballapi.model.PlayerModel;
import com.graphql.basketballapi.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    public Player addPlayer(PlayerModel playerModel) {
        Player player = modelMapper.map(playerModel, Player.class);
        return playerRepository.save(player);
    }
}
