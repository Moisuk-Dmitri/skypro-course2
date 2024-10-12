package com.skypro_course2.skypro_course2;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.exception.OutOfQuestionSetBoundsException;
import com.skypro_course2.skypro_course2.service.ExaminerServiceImpl;
import com.skypro_course2.skypro_course2.service.QuestionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    private Collection<Question> questionsList;

    @Mock
    private QuestionService questionServiceMock;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void setup() {
        questionsList = List.of(
                new Question("Что такое JVM?", "JVM (Java Virtual Machine) — это виртуальная машина, которая выполняет байт-код Java."),
                new Question("Что такое JDK?", "JDK (Java Development Kit) — это набор инструментов для разработки программ на языке Java."),
                new Question("Что такое JRE?", "JRE (Java Runtime Environment) — это среда выполнения Java-программ, включающая JVM и библиотеки."),
                new Question("Что такое перегрузка методов?", "Перегрузка методов — это создание нескольких методов с одним именем, но с разными параметрами."),
                new Question("Что такое полиморфизм?", "Полиморфизм — это способность объекта принимать множество форм, обычно через наследование и интерфейсы."),
                new Question("Что такое абстрактный класс?", "Абстрактный класс — это класс, который не может быть инстанцирован и содержит абстрактные методы."),
                new Question("Что такое интерфейс?", "Интерфейс — это контракт, который должен реализовать класс. Все методы в интерфейсе по умолчанию абстрактные."),
                new Question("Что такое исключение?", "Исключение — это событие, которое возникает во время выполнения программы и нарушает её нормальное выполнение."),
                new Question("Для чего используется ключевое слово 'final'?", "Ключевое слово 'final' используется для объявления констант, методов, которые нельзя переопределить, и классов, которые нельзя наследовать."),
                new Question("Что такое коллекция в Java?", "Коллекция — это объект, который представляет собой группу объектов (элементов). В Java есть разные типы коллекций, такие как List, Set и Map."));
    }

    @Test
    @DisplayName("Положительный тест на получения списка вопросов и ответов")
    public void shouldReturnListOfQuestion() {
        when(questionServiceMock.getAll()).thenReturn(questionsList);
        when(questionServiceMock.getRandomQuestion()).thenReturn(questionsList.stream().toList().get(new Random().nextInt(questionsList.size())));

        assertThat(examinerService.getQuestions(1)).containsAnyElementsOf(questionsList);

        verify(questionServiceMock, times(1)).getAll();
        verify(questionServiceMock, times(1)).getRandomQuestion();
    }

    @Test
    @DisplayName("Отрицальтельный тест на запрос вопросов больше чем в списке вопросов")
    public void shouldThrowExceptionWhenAmountAboveCorrect() {
        when(questionServiceMock.getAll()).thenReturn(new ArrayList<>());

        assertThrows(OutOfQuestionSetBoundsException.class, () -> examinerService.getQuestions(1));

        verify(questionServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Отрицательный тест на запрос вопросов <= 0")
    public void shouldThrowExceptionWhenAmountBelowCorrect() {
        when(questionServiceMock.getAll()).thenReturn(questionsList);

        assertThrows(OutOfQuestionSetBoundsException.class, () -> examinerService.getQuestions(0));

        verify(questionServiceMock, times(1)).getAll();
        verify(questionServiceMock, never()).getRandomQuestion();
    }
}
