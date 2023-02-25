package com.graphql.basketballapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.basketballapi.controller.PlayerController;
import com.graphql.basketballapi.entity.Player;
import com.graphql.basketballapi.model.PlayerModel;
import com.graphql.basketballapi.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@GraphQlTest(PlayerController.class)
public class PlayerControllerSuccessTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @Test
    void shouldAddPlayer() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("volkan");
        playerModel.setSurname("saydam");
        playerModel.setPosition("SG");
        Map<String, String> playerMap = objectMapper.convertValue(playerModel, Map.class);
        Player player = objectMapper.convertValue(playerModel, Player.class);

        when(playerService.addPlayer(any())).thenReturn(player);

        graphQlTester.documentName("createNewPlayer")
                .variable("player", playerMap)
                .execute()
                .path("addPlayer").entity(Player.class).satisfies(p -> {
                    assertThat(p).usingRecursiveComparison().isEqualTo(player);
                });
    }

    @Test
    void shouldDeletePlayer() {
        Player player = new Player();
        player.setId(1);
        player.setName("volkan");
        player.setSurname("saydam");
        player.setPosition("SG");

        when(playerService.deletePlayer(any())).thenReturn(player.getId());

        graphQlTester.documentName("deletePlayer")
                .variable("id", player.getId())
                .execute()
                .path("deletePlayer")
                .entity(Integer.class)
                .isEqualTo(player.getId());
    }

    @Test
    void souldGetAllPlayers() {
        Player player = new Player();
        player.setId(1);
        player.setName("volkan");
        player.setSurname("saydam");
        player.setPosition("SG");
        List<Player> players = new ArrayList<>();
        players.add(player);

        when(playerService.getAllPlayers()).thenReturn(players);

        graphQlTester
                .documentName("getAllPlayers")
                .execute()
                .path("getAllPlayers")
                .entityList(Player.class)
                .satisfies(playerList -> {
                    assertThat(playerList).hasSize(1);
                    Player returnedPlayer = playerList.get(0);
                    assertThat(returnedPlayer.getName()).isEqualTo(player.getName());
                    assertThat(returnedPlayer.getSurname()).isEqualTo(player.getSurname());
                    assertThat(returnedPlayer.getPosition()).isEqualTo(player.getPosition());
                });
    }
}
