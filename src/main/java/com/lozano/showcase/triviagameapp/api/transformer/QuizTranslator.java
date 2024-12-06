package com.lozano.showcase.triviagameapp.api.transformer;

import com.lozano.showcase.triviagameapp.api.model.Quiz;
import com.lozano.showcase.triviagameapp.domain.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class QuizTranslator extends Translator<Quiz, com.lozano.showcase.triviagameapp.domain.model.Quiz>{

    private QuestionTranslator questionTranslator;

    @Autowired
    public QuizTranslator(QuestionTranslator questionTranslator) {
        this.questionTranslator = questionTranslator;
    }

    @Override
    public Quiz toApiModel(com.lozano.showcase.triviagameapp.domain.model.Quiz domainQuiz) {


        if (domainQuiz!=null){
            Quiz apiQuiz = new Quiz();
            apiQuiz.setId(domainQuiz.getId());
            apiQuiz.setAuthorId(domainQuiz.getAuthorId());
            apiQuiz.setTitle(domainQuiz.getTitle());
            apiQuiz.setCreateDateTime(domainQuiz.getCreateDateTime());
            apiQuiz.setLastModifiedDateTime(domainQuiz.getLastModifiedDateTime());
            apiQuiz.setDescription(domainQuiz.getDescription());

            List<com.lozano.showcase.triviagameapp.api.model.Question> apiQuestionList = new ArrayList<>();
            if (!domainQuiz.getQuestions().isEmpty()){
                for (Question domainQuestion : domainQuiz.getQuestions()){
                    apiQuestionList.add(this.questionTranslator.toApiModel(domainQuestion));
                }
            }
            apiQuiz.setQuestions(apiQuestionList);
            return apiQuiz;

        } else {
            return null;
        }

    }

    @Override
    public com.lozano.showcase.triviagameapp.domain.model.Quiz toDomainModel(Quiz apiQuiz) {

        if (apiQuiz!=null){
            com.lozano.showcase.triviagameapp.domain.model.Quiz domainQuiz = new com.lozano.showcase.triviagameapp.domain.model.Quiz();
            domainQuiz.setId(apiQuiz.getId());
            domainQuiz.setTitle(apiQuiz.getTitle());
            domainQuiz.setDescription(apiQuiz.getDescription());

            List<Question> domainQuestionsList = new ArrayList<>();
            if (!apiQuiz.getQuestions().isEmpty()){
                for (com.lozano.showcase.triviagameapp.api.model.Question apiQuestion : apiQuiz.getQuestions()){
                    domainQuestionsList.add(this.questionTranslator.toDomainModel(apiQuestion));
                }
            }
            domainQuiz.setQuestions(domainQuestionsList);
            return domainQuiz;
        }
        return null;

    }
}
