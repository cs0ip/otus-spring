package ru.otus.service;

import lombok.Data;

import java.util.List;

public interface CSVService {

    List<Question> getQuestions();
    List<Answer> getAnswers();

    @Data
    class Question {
        private final int id;
        private final String text;
    }

    @Data
    class Answer {
        private final int id;
        private final int questionId;
        private final String text;
        private final boolean isCorrect;
    }
}
