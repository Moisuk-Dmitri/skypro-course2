package com.skypro_course2.skypro_course2.service;

import com.skypro_course2.skypro_course2.domain.Question;
import com.skypro_course2.skypro_course2.exception.AddingDuplicateQuestionException;
import com.skypro_course2.skypro_course2.exception.EmptyListOfQuestionsException;
import com.skypro_course2.skypro_course2.exception.RemovingNonExistentQuestionException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {

    private Set<Question> questionSet;

    public JavaQuestionRepository() {
        questionSet = new HashSet<>();
    }

    @PostConstruct
    private void init() {
        questionSet.add(new Question(
                "Что такое класс в Java?",
                "Класс в Java — это шаблон или чертеж для создания объектов. Он определяет атрибуты (поля) и поведение (методы), которые объект может иметь."));
        questionSet.add(new Question(
                "Как объявить переменную в Java?",
                "Для объявления переменной в Java необходимо указать тип данных переменной, а затем ее имя. Например: int number;"));
        questionSet.add(new Question(
                "Что такое объект в Java?",
                "Объект в Java — это экземпляр класса. Он содержит состояние (значения полей) и методы для взаимодействия с этим состоянием."));
        questionSet.add(new Question(
                "Что такое наследование в Java?",
                "Наследование — это механизм, позволяющий одному классу (подклассу) унаследовать свойства и методы другого класса (суперкласса). Это способствует повторному использованию кода и расширяемости."));
        questionSet.add(new Question(
                "Чем отличается интерфейс от абстрактного класса в Java?",
                "Интерфейс определяет только поведение (методы без реализации), а абстрактный класс может содержать как абстрактные, так и реализованные методы. Класс может реализовывать несколько интерфейсов, но может наследовать только один абстрактный класс."));
    }

    @Override
    public Question add(String question, String answer) {
        if (questionSet.contains(new Question(question, answer))) {
            throw new AddingDuplicateQuestionException("Question already assigned");
        }

        questionSet.add(new Question(question, answer));

        return new Question(question, answer);
    }

    @Override
    public Question add(Question question) {
        if (questionSet.contains(question)) {
            throw new AddingDuplicateQuestionException("Question already assigned");
        }

        questionSet.add(question);

        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question questionFull = new Question(question, answer);

        if (!questionSet.contains(questionFull)) {
            throw new RemovingNonExistentQuestionException("Question cant be found");
        }

        questionSet.remove(questionFull);

        return questionFull;
    }

    @Override
    public Question remove(Question question) {
        if (!questionSet.contains(question)) {
            throw new RemovingNonExistentQuestionException("Question cant be found");
        }

        questionSet.remove(question);

        return question;
    }

    @Override
    public Collection<Question> getAll() {
        if (questionSet.isEmpty()) {
            throw new EmptyListOfQuestionsException("Empty question set");
        }

        return questionSet;
    }
}
