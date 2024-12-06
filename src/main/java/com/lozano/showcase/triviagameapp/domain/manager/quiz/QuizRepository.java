package com.lozano.showcase.triviagameapp.domain.manager.quiz;

import com.lozano.showcase.triviagameapp.domain.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, String> {

}
