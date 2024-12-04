package com.lozano.showcase.triviagameapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "QUESTION_ID")
    private String id;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "ANSWER_ONE")
    private String answerOne;

    @Column(name = "ANSWER_TWO")
    private String answerTwo;

    @Column(name = "ANSWER_THREE")
    private String answerThree;

    @Column(name = "ANSWER_FOUR")
    private String answerFour;

    @Column(name = "CORRECT_ANSWER")
    private Integer correctAnswerIndex;

    @Column(name = "ASK_ORDER_INDEX")
    private Integer askOrderIndex;





}
