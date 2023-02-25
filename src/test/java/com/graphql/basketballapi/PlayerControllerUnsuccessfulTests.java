package com.graphql.basketballapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.basketballapi.controller.PlayerController;
import com.graphql.basketballapi.model.PlayerModel;
import com.graphql.basketballapi.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.execution.ErrorType;

import java.util.Map;

import static graphql.ErrorType.ValidationError;
import static graphql.ErrorType.NullValueInNonNullableField;

@GraphQlTest(PlayerController.class)
public class PlayerControllerUnsuccessfulTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @Test
    void whenPlayerIsNull_thenResponseWithNullValueInNonNullableField() {
        graphQlTester.documentName("createNewPlayer")
                .variable("player", null)
                .execute()
                .errors()
                .expect(error -> error.getErrorType() == NullValueInNonNullableField)
                .verify();
    }
    @Test
    void whenPlayerNameNull_thenResponseWithValidationError() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setSurname("saydam");
        playerModel.setPosition("SG");
        Map<String, String> playerMap = objectMapper.convertValue(playerModel, Map.class);

        graphQlTester.documentName("createNewPlayer")
                .variable("player", playerMap)
                .execute()
                .errors()
                .expect(error -> error.getErrorType() == ValidationError)
                .verify();
    }

    @Test
    void whenPlayerSurnameNull_thenResponseWithValidationError() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("volkan");
        playerModel.setPosition("SG");
        Map<String, String> playerMap = objectMapper.convertValue(playerModel, Map.class);

        graphQlTester.documentName("createNewPlayer")
                .variable("player", playerMap)
                .execute()
                .errors()
                .expect(error -> error.getErrorType() == ValidationError)
                .verify();
    }

    @Test
    void whenPlayerPositionNull_thenResponseWithValidationError() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("volkan");
        playerModel.setSurname("saydam");
        Map<String, String> playerMap = objectMapper.convertValue(playerModel, Map.class);

        graphQlTester.documentName("createNewPlayer")
                .variable("player", playerMap)
                .execute()
                .errors()
                .expect(error -> error.getErrorType() == ValidationError)
                .verify();
    }

    @Test
    void whenPlayerPositionInvalid_thenResponseWithValidationError() {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName("volkan");
        playerModel.setSurname("saydam");
        playerModel.setPosition("AK");
        Map<String, String> playerMap = objectMapper.convertValue(playerModel, Map.class);

        graphQlTester.documentName("createNewPlayer")
                .variable("player", playerMap)
                .execute()
                .errors()
                .expect(error -> error.getErrorType() == ValidationError)
                .verify();
    }

    @Test
    void whenDeleteIdNull_thenResponseWithValidationError() {
        graphQlTester.documentName("deletePlayer")
                .variable("id", null)
                .execute()
                .errors()
                .expect(error -> error.getErrorType() == ValidationError)
                .verify();
    }

    @Test
    void whenDeleteIdInvalid_thenResponseWithInternalError() {
        graphQlTester.documentName("deletePlayer")
                .variable("id", "abc")
                .execute()
                .errors()
                .expect(error -> error.getErrorType() == ErrorType.INTERNAL_ERROR)
                .verify();
    }


}
