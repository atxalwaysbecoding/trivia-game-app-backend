package com.lozano.showcase.triviagameapp.domain.manager.quiz;

import com.lozano.showcase.triviagameapp.domain.model.Quiz;

import java.util.List;

public interface QuizManager {

    Quiz getQuizByID(String id);

    List<Quiz> getAllQuizzesByAuthorId(String id);

    Quiz createQuiz(Quiz quiz, String userId);

    Quiz updateQuiz(Quiz quiz, String userId);

    boolean deleteQuizById(String id, String userId);




}
