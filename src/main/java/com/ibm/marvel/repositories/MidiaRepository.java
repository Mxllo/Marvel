package com.ibm.marvel.repositories;

import com.ibm.marvel.model.Midia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidiaRepository extends JpaRepository<Midia, Integer> {
}
