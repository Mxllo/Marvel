package com.ibm.marvel.repositories;

import com.ibm.marvel.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Integer> {
    Optional<Ator> findByNome(String nome);
}
