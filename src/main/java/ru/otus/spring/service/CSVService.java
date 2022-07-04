package ru.otus.spring.service;

import lombok.Data;

import java.util.List;

public interface CSVService {

    List<Question> getQuestions();
    List<Answer> getAnswers();

    @Data
    class Question {
        private final int id;
        private final String question;
    }

    @Data
    class Answer {
        private final int id;
        private final String question;
    }
}
