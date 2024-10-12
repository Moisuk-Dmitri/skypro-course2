package com.skypro_course2.skypro_course2;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.exception.AddingQuestionException;
import com.skypro_course2.skypro_course2.exception.EmptyQuestionSetException;
import com.skypro_course2.skypro_course2.exception.RemovingQuestionException;
import com.skypro_course2.skypro_course2.service.JavaQuestionService;
import com.skypro_course2.skypro_course2.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;

    Question question;

    @BeforeEach
    public void setup() {
        javaQuestionService = new JavaQuestionService();

        question = new Question("Что такое JVM?", "JVM (Java Virtual Machine) — это виртуальная машина, которая выполняет байт-код Java.");
    }

    @Test
    @DisplayName("Положительный тест на добавление вопроса через поля")
    public void shouldAddQuestionToSetByFields() {
        javaQuestionService.add(question.getQuestion(), question.getAnswer());

        assertEquals(1, javaQuestionService.getAll().size());
    }

    @Test
    @DisplayName("Отрицательный тест на добавление вопроса через объект класса")
    public void shouldThrowExceptionWhenObjAlreadyAssignedByFields() {
        javaQuestionService.add(question.getQuestion(), question.getAnswer());

        assertThrows(AddingQuestionException.class, () -> javaQuestionService.add(question.getQuestion(), question.getAnswer()));
    }

    @Test
    @DisplayName("Положительный тест на добавление вопроса через объект класса")
    public void shouldAddQuestionToSetByObject() {
        javaQuestionService.add(question);

        assertEquals(1, javaQuestionService.getAll().size());
    }

    @Test
    @DisplayName("Отрицательный тест на добавление вопроса через объект класса")
    public void shouldThrowExceptionWhenObjAlreadyAssignedByObject() {
        javaQuestionService.add(question);

        assertThrows(AddingQuestionException.class, () -> javaQuestionService.add(question));
    }

    @Test
    @DisplayName("Положительный тест на удаление вопроса")
    public void shouldRemoveObject() {
        javaQuestionService.add(question);

        assertEquals(1, javaQuestionService.getAll().size());

        javaQuestionService.remove(question);

        assertThrows(EmptyQuestionSetException.class, () -> javaQuestionService.getAll().size());
    }

    @Test
    @DisplayName("Отрицательный тест на удаление несуществующего вопроса")
    public void shouldThrowExceptionWhenRemoveObject() {
        assertThrows(RemovingQuestionException.class, () -> javaQuestionService.remove(question));
    }

    @Test
    @DisplayName("Положительный тест на вывод списка вопросов")
    public void shouldReturnQuestionSet() {
        javaQuestionService.add(question);
        Collection<Question> expected = List.of(question);

        assertEquals(expected, javaQuestionService.getAll());
    }

    @Test
    @DisplayName("Отрицательный тест на вывод списка вопросов")
    public void shouldThrowExceptionWhenNoSetToGet() {
        assertThrows(EmptyQuestionSetException.class, () -> javaQuestionService.getAll());
    }

    @Test
    @DisplayName("Отрицательный тест на вывод случайного вопроса")
    public void shouldThrowExceptionWhenNoQuestionToGet() {
        assertThrows(EmptyQuestionSetException.class, () -> javaQuestionService.getRandomQuestion());
    }
}
