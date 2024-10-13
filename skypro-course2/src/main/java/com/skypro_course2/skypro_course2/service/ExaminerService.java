package com.skypro_course2.skypro_course2.service;

import com.skypro_course2.skypro_course2.domain.Question;

import java.util.Collection;
import java.util.Collections;

public interface ExaminerService {

    public Collection<Question> getQuestions(int amount);
}
