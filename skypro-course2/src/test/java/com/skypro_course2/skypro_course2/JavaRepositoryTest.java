package com.skypro_course2.skypro_course2;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.exception.AddingDuplicateQuestionException;
import com.skypro_course2.skypro_course2.exception.EmptyListOfQuestionsException;
import com.skypro_course2.skypro_course2.exception.RemovingNonExistentQuestionException;
import com.skypro_course2.skypro_course2.service.JavaQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JavaRepositoryTest {

    private JavaQuestionRepository javaQuestionRepository;

    Question question;

    @BeforeEach
    public void setup() {
        javaQuestionRepository = new JavaQuestionRepository();
        question = new Question("Что такое JVM?", "JVM (Java Virtual Machine) — это виртуальная машина, которая выполняет байт-код Java.");
    }

    @Test
    @DisplayName("Положительный тест на добавление вопроса через поля")
    public void shouldAddQuestionToSetByFields() {
        javaQuestionRepository.add(question.getQuestion(), question.getAnswer());

        assertEquals(1, javaQuestionRepository.getAll().size());
    }

    @Test
    @DisplayName("Отрицательный тест на добавление вопроса через объект класса")
    public void shouldThrowExceptionWhenObjAlreadyAssignedByFields() {
        javaQuestionRepository.add(question.getQuestion(), question.getAnswer());

        assertThrows(AddingDuplicateQuestionException.class, () -> javaQuestionRepository.add(question.getQuestion(), question.getAnswer()));
    }

    @Test
    @DisplayName("Положительный тест на добавление вопроса через объект класса")
    public void shouldAddQuestionToSetByObject() {
        javaQuestionRepository.add(question);

        assertEquals(1, javaQuestionRepository.getAll().size());
    }

    @Test
    @DisplayName("Отрицательный тест на добавление вопроса через объект класса")
    public void shouldThrowExceptionWhenObjAlreadyAssignedByObject() {
        javaQuestionRepository.add(question);

        assertThrows(AddingDuplicateQuestionException.class, () -> javaQuestionRepository.add(question));
    }

    @Test
    @DisplayName("Положительный тест на удаление вопроса")
    public void shouldRemoveObject() {
        javaQuestionRepository.add(question);

        assertEquals(1, javaQuestionRepository.getAll().size());

        javaQuestionRepository.remove(question);

        assertThrows(EmptyListOfQuestionsException.class, () -> javaQuestionRepository.getAll().size());
    }

    @Test
    @DisplayName("Отрицательный тест на удаление несуществующего вопроса")
    public void shouldThrowExceptionWhenRemoveObject() {
        assertThrows(RemovingNonExistentQuestionException.class, () -> javaQuestionRepository.remove(question));
    }

    @Test
    @DisplayName("Положительный тест на вывод списка вопросов")
    public void shouldReturnQuestionSet() {
        javaQuestionRepository.add(question);
        Collection<Question> expected = List.of(question);

        assertNotNull(javaQuestionRepository.getAll());
    }

    @Test
    @DisplayName("Отрицательный тест на вывод списка вопросов")
    public void shouldThrowExceptionWhenNoSetToGet() {
        assertThrows(EmptyListOfQuestionsException.class, () -> javaQuestionRepository.getAll());
    }
}
