package com.graphql.basketballapi;


import com.graphql.basketballapi.entity.Player;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceSuccessTests {

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
    void whenPlayerValid_thenShouldAddPlayer() {
        when(playerRepositoryMock.count()).thenReturn(0L);

        when(playerRepositoryMock.findByPosition(any())).thenReturn(Optional.empty());

        when(modelMapperMock.map(any(), any())).thenReturn(player);

        when(playerRepositoryMock.save(any())).thenReturn(player);

        assertThat(playerService.addPlayer(playerModel)).isEqualTo(player);
    }

    @Test
    void whenPlayerIdValid_thenShouldDeletePlayer() {
        player.setId(1);

        when(playerRepositoryMock.findById(any())).thenReturn(Optional.of(player));

        assertThat(playerService.deletePlayer(player.getId())).isEqualTo(player.getId());
    }
}
