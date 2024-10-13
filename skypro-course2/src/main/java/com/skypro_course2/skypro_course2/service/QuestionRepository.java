package com.skypro_course2.skypro_course2.service;

import com.skypro_course2.skypro_course2.domain.Question;

import java.util.Collection;

public interface QuestionRepository {

    public Question add(String question, String answer);

    public Question add(Question question);

    public Question remove(String question, String answer);

    public Question remove(Question question);

    public Collection<Question> getAll();
}
