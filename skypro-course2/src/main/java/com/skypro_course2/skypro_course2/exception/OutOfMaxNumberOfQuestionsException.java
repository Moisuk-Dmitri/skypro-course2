package com.skypro_course2.skypro_course2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OutOfMaxNumberOfQuestionsException extends RuntimeException {

    public OutOfMaxNumberOfQuestionsException(Exception e) {
        super(e);
    }

    public OutOfMaxNumberOfQuestionsException(String message) {
        System.out.println(message);
    }
}
