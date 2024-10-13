package com.skypro_course2.skypro_course2;

import com.skypro_course2.skypro_course2.service.MathQuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    private MathQuestionService mathQuestionService;

    @Test
    @DisplayName("Положительный тест на построение уравнения")
    void shouldReturnEquation() {
        assertNotNull(mathQuestionService.getRandomQuestion());
    }
}
