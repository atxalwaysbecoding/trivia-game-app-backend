package com.lozano.showcase.triviagameapp.api.controller;

import com.lozano.showcase.triviagameapp.api.model.Quiz;
import com.lozano.showcase.triviagameapp.api.transformer.QuizTranslator;
import com.lozano.showcase.triviagameapp.domain.manager.quiz.QuizManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/quiz")
public class QuizController {

    private QuizTranslator quizTranslator;
    private QuizManager quizManager;

    @Autowired
    public QuizController(QuizTranslator quizTranslator, QuizManager quizManager) {
        this.quizTranslator = quizTranslator;
        this.quizManager = quizManager;
    }

    @RequestMapping(value="/{id}")
    public ResponseEntity getQuizById(@PathVariable String id){
        return ResponseEntity.ok(this.quizTranslator.toApiModel(this.quizManager.getQuizByID(id)));
    }

    @PostMapping
    public ResponseEntity createQuiz(@RequestBody Quiz quiz){
        //todo - get and set userId
        com.lozano.showcase.triviagameapp.domain.model.Quiz domainQuiz = this.quizManager.createQuiz(this.quizTranslator.toDomainModel(quiz), "userId123");
        return ResponseEntity.ok(this.quizTranslator.toApiModel(domainQuiz));
    }

    @PutMapping
    public ResponseEntity updateQuiz(@RequestBody Quiz quiz){
        //todo - get and set userId
        com.lozano.showcase.triviagameapp.domain.model.Quiz domainQuiz = this.quizManager.updateQuiz(this.quizTranslator.toDomainModel(quiz), "userId123");
        return ResponseEntity.ok(this.quizTranslator.toApiModel(domainQuiz));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteQuiz(@PathVariable String id){
        //todo - get and set userId
        return ResponseEntity.ok(this.quizManager.deleteQuizById(id, "userId123"));
    }
}
