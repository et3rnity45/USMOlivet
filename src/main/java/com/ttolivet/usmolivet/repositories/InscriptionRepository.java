package com.ttolivet.usmolivet.repositories;

import com.ttolivet.usmolivet.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    Inscription findTopByOrderByIdDesc();

}
