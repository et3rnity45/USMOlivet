package com.ttolivet.usmolivet.repositories;

import com.ttolivet.usmolivet.entities.Poule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PouleRepository extends JpaRepository<Poule, Long> {
}
