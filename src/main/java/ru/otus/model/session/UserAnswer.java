package ru.otus.model.session;

import lombok.Data;

@Data
public class UserAnswer {
    private final int questionId;
    private final int answerId;
    private final boolean isCorrect;
}