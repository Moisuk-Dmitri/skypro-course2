package com.skypro_course2.skypro_course2.service;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.exception.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component("MathQuestionService")
public class MathQuestionService implements QuestionService {

    @Override
    public Question add(String question, String answer) {
        throw new MathMethodNotExistException("Does not support math add method");
    }

    @Override
    public Question add(Question question) {
        throw new MathMethodNotExistException("Does not support math add method");
    }

    @Override
    public Question remove(String question, String answer) {
        throw new MathMethodNotExistException("Does not support math remove method");
    }

    @Override
    public Question remove(Question question) {
        throw new MathMethodNotExistException("Does not support math remove method");
    }

    @Override
    public Collection<Question> getAll() {
        throw new MathMethodNotExistException("Does not support math getAll method");
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();

        String answer = "";

        int num1 = random.nextInt(100);
        int num2 = random.nextInt(100);
        int randomSign = random.nextInt(4);
        String sign = "";
        switch (randomSign) {
            case 0:
                sign = Character.toString((char) 42);
                answer = Double.toString((double) num1 * num2);
                break;
            case 1:
                sign = Character.toString((char) 43);
                answer = Double.toString((double) num1 + num2);
                break;
            case 2:
                sign = Character.toString((char) 45);
                answer = Double.toString((double) num1 - num2);
                break;
            case 3:
                sign = Character.toString((char) 47);
                answer = Double.toString((double) num1 / num2);
                break;
            default:
                throw new MathEquationBuildingException("Could not generate equation");
        }

        return new Question(Integer.toString(num1) + " " + sign + " " + Integer.toString(num2), answer);
    }
}
