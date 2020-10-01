package com.ttolivet.usmolivet.repositories;

import com.ttolivet.usmolivet.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findAllByOrderByPointsDesc();
}
