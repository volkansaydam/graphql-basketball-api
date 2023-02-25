package com.graphql.basketballapi.repository;

import com.graphql.basketballapi.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
