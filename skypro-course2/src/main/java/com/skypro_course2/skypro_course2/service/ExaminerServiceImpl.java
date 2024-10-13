package com.skypro_course2.skypro_course2.service;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.exception.OutOfMaxNumberOfQuestionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    Collection<QuestionService> questionServices;

    public ExaminerServiceImpl(@Qualifier("JavaQuestionService") QuestionService javaQuestionService, @Qualifier("MathQuestionService") QuestionService mathQuestionService) {
        questionServices = new ArrayList<>();
        questionServices.add(javaQuestionService);
        questionServices.add(mathQuestionService);
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionServices.stream().toList().get(0).getAll().size()) {
            throw new OutOfMaxNumberOfQuestionsException("Not enough questions to show");
        }

        if (amount <= 0) {
            throw new OutOfMaxNumberOfQuestionsException("amount must be positive");
        }

        Random random = new Random();

        Collection<Question> questionSet = new HashSet<>();
        int javaQuestionsAmount = 0;
        if (amount == 1) {
            if ((random.nextInt(100)) > 50) {
                javaQuestionsAmount = 1;
            }
        } else {
            javaQuestionsAmount = random.nextInt(amount);

        }


        while (questionSet.size() < javaQuestionsAmount) {
            questionSet.add(questionServices.stream().toList().get(0).getRandomQuestion());
        }

        while (questionSet.size() < amount) {
            questionSet.add(questionServices.stream().toList().get(1).getRandomQuestion());
        }

        return questionSet;
    }
}
