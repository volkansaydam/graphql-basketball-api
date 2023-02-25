package com.graphql.basketballapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.basketballapi.controller.PlayerController;
import com.graphql.basketballapi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(PlayerController.class)
public class PlayerControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;
}
