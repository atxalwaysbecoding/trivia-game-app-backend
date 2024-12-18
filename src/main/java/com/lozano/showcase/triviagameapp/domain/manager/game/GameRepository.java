package com.lozano.showcase.triviagameapp.domain.manager.game;

import com.lozano.showcase.triviagameapp.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
}
