package com.lozano.showcase.triviagameapp.api.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class Question {

    private String id;

    @NotBlank(message = "question cannot be blank")
    @NotNull(message = "question cannot be null")
    @Pattern(regexp = "[a-zA-Z0-9 ]*", message = "question must not contain special characters")
    @Size(max = 100, message = "question exceeds max length")
    private String question;

    @NotBlank(message = "answer one cannot be blank")
    @NotNull(message = "answer one cannot be null")
    @Pattern(regexp = "[a-zA-Z0-9 ]*", message = "answer one must not contain special characters")
    @Size(max = 60, message = "answer one exceeds max length")
    private String answerOne;

    @NotBlank(message = "answer two cannot be blank")
    @NotNull(message = "answer two cannot be null")
    @Pattern(regexp = "[a-zA-Z0-9 ]*", message = "answer two must not contain special characters")
    @Size(max = 60, message = "answer two exceeds max length")
    private String answerTwo;

    @NotBlank(message = "answer three cannot be blank")
//    @NotNull(message = "answer three cannot be null")
    @Pattern(regexp = "[a-zA-Z0-9 ]*", message = "answer three must not contain special characters")
    @Size(max = 60, message = "answer three exceeds max length")
    private String answerThree;

    @NotBlank(message = "answer four cannot be blank")
//    @NotNull(message = "answer four cannot be null")
    @Pattern(regexp = "[a-zA-Z0-9 ]*", message = "answer four must not contain special characters")
    @Size(max = 60, message = "answer four exceeds max length")
    private String answerFour;

    @Max(value = 4, message = "invalid answer index")
    @Min(value = 1, message = "invalid answer index")
    private Integer correctAnswerIndex;

    @Max(value = 4, message = "invalid order index")
    @Min(value = 1, message = "invalid order index")
    private Integer askOrderIndex;
}
