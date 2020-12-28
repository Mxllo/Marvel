package com.ibm.marvel.repositories;

import com.ibm.marvel.model.Criador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriadorRepository extends JpaRepository<Criador, Integer> {
}
