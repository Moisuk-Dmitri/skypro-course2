package com.skypro_course2.skypro_course2;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.exception.AddingDuplicateQuestionException;
import com.skypro_course2.skypro_course2.exception.EmptyListOfQuestionsException;
import com.skypro_course2.skypro_course2.service.JavaQuestionRepository;
import com.skypro_course2.skypro_course2.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    private JavaQuestionRepository javaQuestionRepositoryMock;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    Question question;

    @BeforeEach
    public void setup() {
        question = new Question("Что такое JVM?", "JVM (Java Virtual Machine) — это виртуальная машина, которая выполняет байт-код Java.");
    }

    @Test
    @DisplayName("Положительный тест на добавление вопроса через поля")
    public void shouldAddQuestionToSetByFields() {
        when(javaQuestionRepositoryMock.add(question.getQuestion(), question.getAnswer())).thenReturn(question);

        assertNotNull(javaQuestionService.add(question.getQuestion(), question.getAnswer()));

        verify(javaQuestionRepositoryMock, times(1)).add(question.getQuestion(), question.getAnswer());
    }

    @Test
    @DisplayName("Отрицательный тест на добавление вопроса через объект класса")
    public void shouldThrowExceptionWhenObjAlreadyAssignedByFields() {
        when(javaQuestionRepositoryMock.add(question.getQuestion(), question.getAnswer())).thenThrow(AddingDuplicateQuestionException.class);

        assertThrows(AddingDuplicateQuestionException.class, () -> javaQuestionService.add(question.getQuestion(), question.getAnswer()));

        verify(javaQuestionRepositoryMock, times(1)).add(question.getQuestion(), question.getAnswer());
    }

    @Test
    @DisplayName("Положительный тест на добавление вопроса через объект класса")
    public void shouldAddQuestionToSetByObject() {
        when(javaQuestionRepositoryMock.add(question)).thenReturn(question);

        assertNotNull(javaQuestionService.add(question));

        verify(javaQuestionRepositoryMock, times(1)).add(question);
    }

    @Test
    @DisplayName("Отрицательный тест на добавление вопроса через объект класса")
    public void shouldThrowExceptionWhenObjAlreadyAssignedByObject() {
        when(javaQuestionRepositoryMock.add(question)).thenThrow(AddingDuplicateQuestionException.class);

        assertThrows(AddingDuplicateQuestionException.class, () -> javaQuestionService.add(question));

        verify(javaQuestionRepositoryMock, times(1)).add(question);
    }

    @Test
    @DisplayName("Положительный тест на удаление вопроса по полям")
    public void shouldRemoveObjectByField() {
        when(javaQuestionRepositoryMock.remove(question.getQuestion(), question.getAnswer())).thenReturn(question);

        assertNotNull(javaQuestionService.remove(question.getQuestion(), question.getAnswer()));

        verify(javaQuestionRepositoryMock, times(1)).remove(question.getQuestion(), question.getAnswer());
    }

    @Test
    @DisplayName("Отрицательный тест на удаление несуществующего вопроса по полям")
    public void shouldThrowExceptionWhenRemoveObjectByFields() {
        when(javaQuestionRepositoryMock.remove(question.getQuestion(), question.getAnswer())).thenThrow(AddingDuplicateQuestionException.class);

        assertThrows(AddingDuplicateQuestionException.class, () -> javaQuestionService.remove(question.getQuestion(), question.getAnswer()));

        verify(javaQuestionRepositoryMock, times(1)).remove(question.getQuestion(), question.getAnswer());
    }

    @Test
    @DisplayName("Положительный тест на удаление вопроса по объекту")
    public void shouldRemoveObjectByObject() {
        when(javaQuestionRepositoryMock.remove(question)).thenReturn(question);

        assertNotNull(javaQuestionService.remove(question));

        verify(javaQuestionRepositoryMock, times(1)).remove(question);
    }

    @Test
    @DisplayName("Отрицательный тест на удаление несуществующего вопроса по объекту")
    public void shouldThrowExceptionWhenRemoveObjectByObject() {
        when(javaQuestionRepositoryMock.remove(question)).thenThrow(AddingDuplicateQuestionException.class);

        assertThrows(AddingDuplicateQuestionException.class, () -> javaQuestionService.remove(question));

        verify(javaQuestionRepositoryMock, times(1)).remove(question);
    }

    @Test
    @DisplayName("Положительный тест на вывод списка вопросов")
    public void shouldReturnQuestionSet() {
        Collection<Question> questionCollection = List.of(question);

        when(javaQuestionRepositoryMock.getAll()).thenReturn(questionCollection);

        assertNotNull(javaQuestionService.getAll());

        verify(javaQuestionRepositoryMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Отрицательный тест на вывод списка вопросов")
    public void shouldThrowExceptionWhenNoSetToGet() {
        when(javaQuestionRepositoryMock.getAll()).thenThrow(EmptyListOfQuestionsException.class);

        assertThrows(EmptyListOfQuestionsException.class, () -> javaQuestionService.getAll());

        verify(javaQuestionRepositoryMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Положительный тест на вывод случайного вопроса")
    public void shouldReturnRandomQuestion() {
        Collection<Question> questionCollection = List.of(question);

        when(javaQuestionRepositoryMock.getAll()).thenReturn(questionCollection);

        assertNotNull(javaQuestionService.getRandomQuestion());

        verify(javaQuestionRepositoryMock, times(2)).getAll();
    }

    @Test
    @DisplayName("Отрицательный тест на вывод случайного вопроса")
    public void shouldThrowExceptionWhenNoQuestionToGet() {
        when(javaQuestionRepositoryMock.getAll()).thenThrow(EmptyListOfQuestionsException.class);

        assertThrows(EmptyListOfQuestionsException.class, () -> javaQuestionService.getRandomQuestion());

        verify(javaQuestionRepositoryMock, times(1)).getAll();
    }
}
