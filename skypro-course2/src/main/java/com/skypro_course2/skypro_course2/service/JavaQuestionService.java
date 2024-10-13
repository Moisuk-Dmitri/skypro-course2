package com.skypro_course2.skypro_course2.service;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.exception.AddingQuestionException;
import com.skypro_course2.skypro_course2.exception.EmptyQuestionSetException;
import com.skypro_course2.skypro_course2.exception.RemovingQuestionException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private Set<Question> questionSet;

    public JavaQuestionService() {
        questionSet = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        if (questionSet.contains(new Question(question, answer)))
        {
            throw new AddingQuestionException(new Exception("Question already assigned"));
        }

            questionSet.add(new Question(question, answer));

        return new Question(question, answer);
    }

    @Override
    public Question add(Question question) {
        if (questionSet.contains(question))
        {
            throw new AddingQuestionException(new Exception("Question already assigned"));
        }

        questionSet.add(question);

        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionSet.contains(question))
        {
            throw new RemovingQuestionException(new Exception("Question cant be found"));
        }

        questionSet.remove(question);

        return question;
    }

    @Override
    public Collection<Question> getAll() {
        if (questionSet.isEmpty()) {
            throw new EmptyQuestionSetException(new Exception("Empty question set"));
        }

        return questionSet.stream()
                .toList();
    }

    @Override
    public Question getRandomQuestion() {
        if (questionSet.isEmpty()) {
            throw new EmptyQuestionSetException(new Exception("Empty question set"));
        }

        return questionSet.stream()
                .toList()
                .get(new Random().nextInt(questionSet.size()));
    }
}
