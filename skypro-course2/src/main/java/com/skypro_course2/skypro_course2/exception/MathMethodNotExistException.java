package com.skypro_course2.skypro_course2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MathMethodNotExistException extends RuntimeException {

    public MathMethodNotExistException(Exception e) {
        super(e);
    }

    public MathMethodNotExistException(String message) {
        System.out.println(message);
    }
}
