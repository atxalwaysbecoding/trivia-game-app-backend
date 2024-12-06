package com.lozano.showcase.triviagameapp.domain.manager.quiz;

import com.lozano.showcase.triviagameapp.domain.model.Quiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QuizManagerImpl implements QuizManager {

    private QuizRepository quizRepository;

    @Autowired
    public QuizManagerImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz getQuizByID(String id) {
        Optional<Quiz> quizOption = this.quizRepository.findById(id);
        return quizOption.orElse(null);
    }

    @Override
    public List<Quiz> getAllQuizzesByAuthorId(String id) {
        return new ArrayList<>();
    }

    @Override
    public Quiz createQuiz(Quiz quiz, String userId) {
        quiz.setAuthorId(userId);
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz, String userId) {
        Quiz existingQuiz = this.validateUpdateOrDeleteRequest(quiz.getId(), userId);
        quiz.setAuthorId(existingQuiz.getAuthorId());
        return this.quizRepository.save(quiz);
    }

    @Override
    public boolean deleteQuizById(String id, String userId) {
        this.validateUpdateOrDeleteRequest(id, userId);
        this.quizRepository.deleteById(id);
        return true;
    }

    private Quiz validateUpdateOrDeleteRequest(String quizId, String userId){

        Quiz existingQuiz = this.getQuizByID(quizId);
        if (existingQuiz==null){
            // todo - throw error
        } else {
            if (!existingQuiz.getAuthorId().equalsIgnoreCase(userId)){
                //todo - throw error
            }
        }

        return existingQuiz;

    }
}
