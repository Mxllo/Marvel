package com.ibm.marvel.repositories;

import com.ibm.marvel.model.Heroi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Integer> {
    Optional<Heroi> findByNome(String heroi);
}
