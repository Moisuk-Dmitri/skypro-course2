package com.skypro_course2.skypro_course2.controller;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.service.ExaminerServiceImpl;
import com.skypro_course2.skypro_course2.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerServiceImpl examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
