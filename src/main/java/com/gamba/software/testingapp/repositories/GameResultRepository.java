package com.gamba.software.testingapp.repositories;

import com.gamba.software.testingapp.entities.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameResultRepository extends JpaRepository<GameResult, Long> {
}
