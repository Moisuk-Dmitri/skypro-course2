package com.skypro_course2.skypro_course2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MathEquationBuildingException extends RuntimeException {
    public MathEquationBuildingException(Exception e) {
        super(e);
    }

    public MathEquationBuildingException(String message) {
        System.out.println(message);
    }
}
