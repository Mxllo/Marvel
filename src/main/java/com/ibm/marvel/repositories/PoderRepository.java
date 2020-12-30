package com.ibm.marvel.repositories;

import com.ibm.marvel.model.Poder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoderRepository extends JpaRepository<Poder, Integer> {
    Optional<Poder> findByNome(String nome);
}
