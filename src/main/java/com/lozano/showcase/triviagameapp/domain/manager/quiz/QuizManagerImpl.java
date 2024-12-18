package com.lozano.showcase.triviagameapp.domain.manager.quiz;

import com.lozano.showcase.triviagameapp.Utils.ExceptionUtils;
import com.lozano.showcase.triviagameapp.api.config.exception.AppException;
import com.lozano.showcase.triviagameapp.api.config.exception.ExceptionEnum;
import com.lozano.showcase.triviagameapp.domain.model.Question;
import com.lozano.showcase.triviagameapp.domain.model.Quiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class QuizManagerImpl implements QuizManager {

    private Integer maxQuestionsListSize;
    private QuizRepository quizRepository;

    @Autowired
    public QuizManagerImpl(@Value("${quiz.question.list.maxSize}") Integer maxQuestionsListSize, QuizRepository quizRepository) {
        this.maxQuestionsListSize = maxQuestionsListSize;
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
        this.validateAnswerAndOrderIndex(quiz);
        return this.quizRepository.save(this.applyStandardizedCasing(quiz));
    }

    @Override
    public Quiz updateQuiz(Quiz quiz, String userId) {
        Quiz existingQuiz = this.validateUpdateOrDeleteRequest(quiz.getId(), userId);
        quiz.setAuthorId(existingQuiz.getAuthorId());
        this.validateAnswerAndOrderIndex(quiz);
        return this.quizRepository.save(this.applyStandardizedCasing(quiz));
    }

    @Override
    public boolean deleteQuizById(String id, String userId) {
        this.validateUpdateOrDeleteRequest(id, userId);
        this.quizRepository.deleteById(id);
        return true;
    }

    private void validateNumberOfQuestions(Quiz quiz){
        if (quiz!=null) {
            if (quiz.getQuestions()==null || quiz.getQuestions().isEmpty()) {
                ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1003, "quiz questions list is null or empty"));
            } else if (quiz.getQuestions().size()<1 || quiz.getQuestions().size()>this.maxQuestionsListSize){
                ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1004, "quiz questions list is not an allowed size"));
            }
        } else {
            ExceptionUtils.throwException(new AppException(ExceptionEnum.UN5002, "null quiz provided"));
        }
    }

    private void validateAnswerAndOrderIndex(Quiz quiz){

        if (quiz!=null){
            if (!quiz.getQuestions().isEmpty()){

                List<Integer> orderIndexList = new ArrayList<>();
                Set<Integer> orderIndexSet = new HashSet<>();

                for (Question question : quiz.getQuestions()){

                    orderIndexList.add(question.getAskOrderIndex());
                    orderIndexSet.add(question.getAskOrderIndex());

                    if (question.getCorrectAnswerIndex()>4 || question.getCorrectAnswerIndex()<1){
                        ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1000, "answer index cannot be less than 1 or greater than 4"));
                    }
                    if ((question.getAnswerOne()==null || question.getAnswerOne().isEmpty()) || (question.getAnswerTwo()==null || question.getAnswerTwo().isEmpty())){
                        ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1000, "answer 1 and 2 must be present"));
                    }
                    if ((question.getAnswerThree()==null || question.getAnswerThree().isEmpty()) && (question.getAnswerFour()!=null && !question.getAnswerFour().isEmpty())){
                        ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1000, "answer 3 must be present if answer 4 is present"));
                    }
                    if ((question.getAnswerFour()==null || question.getAnswerFour().isEmpty()) && question.getCorrectAnswerIndex().equals(4)){
                        ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1000, "answer index 4 cannot be true if answer 4 is not present"));
                    }
                    if ((question.getAnswerThree()==null || question.getAnswerThree().isEmpty()) && question.getCorrectAnswerIndex().equals(3)){
                        ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1000, "answer index 3 cannot be true if answer 3 is not present"));
                    }
                    if ((question.getAnswerThree()==null || question.getAnswerThree().isEmpty()) && (question.getAnswerFour()==null || question.getAnswerFour().isEmpty())){
                        if (question.getCorrectAnswerIndex().equals(3) || question.getCorrectAnswerIndex().equals(4)){
                            ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1000, "answer index 3 or 4 cannot be true if answer 3 and 4 are not present"));
                        }
                    }
                }

                if (orderIndexList.size() != orderIndexSet.size()){
                    ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1000, "order index list is invalid - not unique entries"));
                }
                Collections.sort(orderIndexList);
                if (!orderIndexList.get(0).equals(1) || !orderIndexList.get(orderIndexList.size()-1).equals(orderIndexList.size())){
                    ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1000, "order index list is invalid - not continuous and/or not starting with 1"));
                }
            }
        }
    }

    private Quiz validateUpdateOrDeleteRequest(String quizId, String userId){

        Quiz existingQuiz = this.getQuizByID(quizId);
        if (existingQuiz==null){
            ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1001, "no existing quiz with Id"));
        } else {
            if (!existingQuiz.getAuthorId().equalsIgnoreCase(userId)){
                ExceptionUtils.throwException(new AppException(ExceptionEnum.QZ1002, "authorId does not match quiz authorId value"));
            }
        }

        return existingQuiz;
    }

    private Quiz applyStandardizedCasing(Quiz quiz){
        if (quiz!=null){
            if (quiz.getId()!=null && !quiz.getId().isEmpty()){
                quiz.setId(quiz.getId().toLowerCase());
            }
            if (quiz.getTitle()!=null && !quiz.getTitle().isEmpty()){
                quiz.setTitle(quiz.getTitle().toLowerCase());
            }
            if (quiz.getDescription()!=null && !quiz.getDescription().isEmpty()){
                quiz.setDescription(quiz.getDescription().toLowerCase());
            }
            if (quiz.getQuestions()!=null && !quiz.getQuestions().isEmpty()){
                for (Question question : quiz.getQuestions()){

                    if (question.getId()!=null && !question.getId().isEmpty()){
                        question.setId(question.getId().toLowerCase());
                    }
                    if (question.getQuestion()!=null && !question.getQuestion().isEmpty()){
                        question.setQuestion(question.getQuestion().toLowerCase());
                    }
                    if (question.getAnswerOne()!=null && !question.getAnswerOne().isEmpty()){
                        question.setAnswerOne(question.getAnswerOne().toLowerCase());
                    }
                    if (question.getAnswerTwo()!=null && !question.getAnswerTwo().isEmpty()){
                        question.setAnswerTwo(question.getAnswerTwo().toLowerCase());
                    }
                    if (question.getAnswerThree()!=null && !question.getAnswerThree().isEmpty()){
                        question.setAnswerThree(question.getAnswerThree().toLowerCase());
                    }
                    if (question.getAnswerFour()!=null && !question.getAnswerFour().isEmpty()){
                        question.setAnswerFour(question.getAnswerFour().toLowerCase());
                    }
                }
            }
        }
        return quiz;
    }
}
