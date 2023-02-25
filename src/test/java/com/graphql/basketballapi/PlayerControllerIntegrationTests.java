package com.graphql.basketballapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.basketballapi.entity.Player;
import com.graphql.basketballapi.model.PlayerModel;
import com.graphql.basketballapi.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BasketballapiApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlayerControllerIntegrationTests {
    @Autowired
    private HttpGraphQlTester graphQlTester;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void souldGetAllPlayers() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("volkan");
        playerModel.setSurname("saydam");
        playerModel.setPosition("SG");
        playerService.addPlayer(playerModel);

        graphQlTester
                .documentName("getAllPlayers")
                .execute()
                .path("getAllPlayers")
                .entityList(Player.class)
                .satisfies(playerList -> {
                    assertThat(playerList).hasSize(1);
                    Player player = playerList.get(0);
                    assertThat(player.getName()).isEqualTo(playerModel.getName());
                    assertThat(player.getSurname()).isEqualTo(playerModel.getSurname());
                    assertThat(player.getPosition()).isEqualTo(playerModel.getPosition());
                });
    }

    @Test
    void shouldAddPlayer() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("volkan");
        playerModel.setSurname("saydam");
        playerModel.setPosition("SG");
        Map<String, String> playerMap = objectMapper.convertValue(playerModel, Map.class);

        graphQlTester.documentName("createNewPlayer")
                .variable("player", playerMap)
                .execute()
                .path("addPlayer.name").entity(String.class).isEqualTo("volkan")
                .path("addPlayer.surname").entity(String.class).isEqualTo("saydam")
                .path("addPlayer.position").entity(String.class).isEqualTo("SG");

    }

    @Test
    void shouldDeletePlayer() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("volkan");
        playerModel.setSurname("saydam");
        playerModel.setPosition("SG");
        Player player = playerService.addPlayer(playerModel);

        graphQlTester.documentName("deletePlayer")
                .variable("id", player.getId())
                .execute()
                .path("deletePlayer")
                .entity(Integer.class)
                .isEqualTo(player.getId());
    }
}
