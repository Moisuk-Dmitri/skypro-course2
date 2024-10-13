package com.skypro_course2.skypro_course2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddingDuplicateQuestionException extends RuntimeException {

    public AddingDuplicateQuestionException(Exception e) {
        super(e);
    }

    public AddingDuplicateQuestionException(String message) {
        System.out.println(message);
    }
}
