package com.skypro_course2.skypro_course2.service;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.exception.OutOfQuestionSetBoundsException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new OutOfQuestionSetBoundsException(new Exception("Not enough questions to show"));
        }

        if (amount < 1) {
            throw new OutOfQuestionSetBoundsException(new Exception("amount must be positive"));
        }

        Collection<Question> questionSet = new HashSet<>();
        while (questionSet.size() < amount) {
            questionSet.add(questionService.getRandomQuestion());
        }

        return questionSet;
    }
}
