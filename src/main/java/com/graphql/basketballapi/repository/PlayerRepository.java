package com.graphql.basketballapi.repository;

import com.graphql.basketballapi.entity.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query("SELECT p FROM Player p WHERE p.position = :position")
    Optional<Player> findByPosition(String position);
}
