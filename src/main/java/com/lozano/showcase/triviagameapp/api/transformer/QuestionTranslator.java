package com.lozano.showcase.triviagameapp.api.transformer;

import com.lozano.showcase.triviagameapp.api.model.Question;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QuestionTranslator extends Translator<Question, com.lozano.showcase.triviagameapp.domain.model.Question>{

    @Override
    public Question toApiModel(com.lozano.showcase.triviagameapp.domain.model.Question domainQuestion) {

        if (domainQuestion!=null){

            Question apiQuestion = new Question();
            apiQuestion.setId(domainQuestion.getId());
            apiQuestion.setQuestion(domainQuestion.getQuestion());
            apiQuestion.setAnswerOne(domainQuestion.getAnswerOne());
            apiQuestion.setAnswerTwo(domainQuestion.getAnswerTwo());
            apiQuestion.setAnswerThree(domainQuestion.getAnswerThree());
            apiQuestion.setAnswerFour(domainQuestion.getAnswerFour());
            apiQuestion.setAskOrderIndex(domainQuestion.getAskOrderIndex());
            apiQuestion.setCorrectAnswerIndex(domainQuestion.getCorrectAnswerIndex());
            return apiQuestion;
        } else {
            return null;
        }
    }

    @Override
    public com.lozano.showcase.triviagameapp.domain.model.Question toDomainModel(Question apiQuestion) {

        if (apiQuestion!=null){

            com.lozano.showcase.triviagameapp.domain.model.Question domainQuestion = new com.lozano.showcase.triviagameapp.domain.model.Question();
            domainQuestion.setId(apiQuestion.getId());
            domainQuestion.setQuestion(apiQuestion.getQuestion());
            domainQuestion.setAnswerOne(apiQuestion.getAnswerOne());
            domainQuestion.setAnswerTwo(apiQuestion.getAnswerTwo());
            domainQuestion.setAnswerThree(apiQuestion.getAnswerThree());
            domainQuestion.setAnswerFour(apiQuestion.getAnswerFour());
            domainQuestion.setAskOrderIndex(apiQuestion.getAskOrderIndex());
            domainQuestion.setCorrectAnswerIndex(apiQuestion.getCorrectAnswerIndex());
            return domainQuestion;
        } else {
            return null;
        }

    }
}
