package com.ibm.marvel.repositories;

import com.ibm.marvel.model.Heroi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Integer> {
}
