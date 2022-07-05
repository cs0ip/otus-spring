package ru.otus.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private final String fio;
    private final List<Answer> answers;

    @Data
    public static class Answer {
        private final int questionId;
        private final int answerId;
        private final boolean isCorrect;
    }
}
