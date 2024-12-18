package com.lozano.showcase.triviagameapp.api.config.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    //global errors
    UN5000("Unknown exception", HttpStatus.INTERNAL_SERVER_ERROR),
    UN5001("Invalid argument exception", HttpStatus.BAD_REQUEST),
    UN5002("Unexpected state", HttpStatus.INTERNAL_SERVER_ERROR),

    //quiz errors
    QZ1000("Invalid answer or order index", HttpStatus.BAD_REQUEST),
    QZ1001("Invalid quiz id provided", HttpStatus.BAD_REQUEST),
    QZ1002("Quiz authorId and requester ID mismatch", HttpStatus.BAD_REQUEST),
    QZ1003("Quiz must contain at least one question", HttpStatus.BAD_REQUEST),
    QZ1004("Quiz question list an invalid size", HttpStatus.BAD_REQUEST);

    public final String message;
    public final HttpStatus httpStatus;

    ExceptionEnum(String message, HttpStatus status) {
        this.message = message;
        this.httpStatus = status;
    }

    public String getMessage(){
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
