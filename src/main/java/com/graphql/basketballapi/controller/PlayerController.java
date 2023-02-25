package com.graphql.basketballapi.controller;

import com.graphql.basketballapi.entity.Player;
import com.graphql.basketballapi.model.PlayerModel;
import com.graphql.basketballapi.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(
            PlayerService playerService
    ) {
        this.playerService = playerService;
    }

    @MutationMapping
    public Player addPlayer(@Argument PlayerModel player) {
        return playerService.addPlayer(player);
    }
}
