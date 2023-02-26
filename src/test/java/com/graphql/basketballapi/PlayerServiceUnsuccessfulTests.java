package com.graphql.basketballapi;

import com.graphql.basketballapi.entity.Player;
import com.graphql.basketballapi.exception.MaxPlayerCountException;
import com.graphql.basketballapi.exception.PlayerAlreadyExistException;
import com.graphql.basketballapi.exception.PlayerNotFoundException;
import com.graphql.basketballapi.model.PlayerModel;
import com.graphql.basketballapi.repository.PlayerRepository;
import com.graphql.basketballapi.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceUnsuccessfulTests {

    @Mock
    private PlayerRepository playerRepositoryMock;

    @Mock
    private ModelMapper modelMapperMock;

    @InjectMocks
    private PlayerService playerService;

    private ModelMapper modelMapper;

    private PlayerModel playerModel;

    private Player player;

    @BeforeEach
    void init() {
        this.modelMapper = new ModelMapper();

        playerModel = new PlayerModel();
        playerModel.setName("volkan");
        playerModel.setSurname("saydam");
        playerModel.setPosition("SG");

        player = modelMapper.map(playerModel, Player.class);
    }

    @Test
    void whenPlayerExist_thenShouldThrowPlayerAlreadyExistException() {
        when(playerRepositoryMock.findByPosition(any())).thenReturn(Optional.of(player));

        Exception exception = assertThrows(PlayerAlreadyExistException.class, () -> {
            playerService.addPlayer(playerModel);
        });

        assertThat(exception.getMessage()).isEqualTo("Failed to add player. Player with position already exist.");
    }

    @Test
    void whenPlayerMaxCountReached_thenShouldThrowMaxPlayerCountException() {
        when(playerRepositoryMock.count()).thenReturn(5L);

        Exception exception = assertThrows(MaxPlayerCountException.class, () -> {
            playerService.addPlayer(playerModel);
        });

        assertThat(exception.getMessage()).isEqualTo("Maximum number of player reached.");
    }

    @Test
    void whenDeletePlayerNotFound_thenShouldThrowPlayerNotFoundException() {
        player.setId(1);
        when(playerRepositoryMock.findById(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(PlayerNotFoundException.class, () -> {
            playerService.deletePlayer(player.getId());
        });

        assertThat(exception.getMessage()).isEqualTo("Player with id: " + player.getId() + " not found");
    }
}
