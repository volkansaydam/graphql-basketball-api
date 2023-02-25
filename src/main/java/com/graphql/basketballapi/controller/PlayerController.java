package com.graphql.basketballapi.controller;

import com.graphql.basketballapi.entity.Player;
import com.graphql.basketballapi.model.PlayerModel;
import com.graphql.basketballapi.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(
            PlayerService playerService
    ) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @MutationMapping
    public Player addPlayer(@Argument PlayerModel player) {
        return playerService.addPlayer(player);
    }

    @MutationMapping
    public Integer deletePlayer(@Argument Integer id) {
        return playerService.deletePlayer(id);
    }
}
