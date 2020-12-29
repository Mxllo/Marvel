package com.ibm.marvel.repositories;

import com.ibm.marvel.model.Poder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoderRepository extends JpaRepository<Poder, Integer> {
    Integer findByNome(String nome);
}
