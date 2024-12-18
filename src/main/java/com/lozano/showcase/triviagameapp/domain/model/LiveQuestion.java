package com.lozano.showcase.triviagameapp.domain.model;

import lombok.Data;

@Data
public class LiveQuestion {

    private String id;

    private String gameId;

    private String question;

    private String answerOne;

    private String answerTwo;

    private String answerThree;

    private String answerFour;

    private Integer submittedAnswerIndex;

    private String submittingParticipantName;
}
