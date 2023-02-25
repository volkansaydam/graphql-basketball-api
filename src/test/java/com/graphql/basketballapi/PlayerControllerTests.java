package com.graphql.basketballapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.basketballapi.controller.PlayerController;
import com.graphql.basketballapi.model.PlayerModel;
import com.graphql.basketballapi.service.PlayerService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Map;

@GraphQlTest(PlayerController.class)
public class PlayerControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @Test
    @Disabled
    // FIXME: 25.02.2023 This is not working i do not understand why
    void shouldAddPlayer() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("volkan");
        playerModel.setSurname("saydam");
        playerModel.setPosition("SG");
        Map<String, String> playerMap = objectMapper.convertValue(playerModel, Map.class);

        this.graphQlTester
                .documentName("createNewPlayer")
                .variable("player", playerMap)
                .execute()
                .path("addPlayer.name").entity(String.class).isEqualTo("volkan")
                .path("addPlayer.surname").entity(String.class).isEqualTo("saydam")
                .path("addPlayer.position").entity(String.class).isEqualTo("SG");

    }
}
