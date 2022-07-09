package ru.otus.model.csv;

import lombok.Data;

@Data
public class CSVAnswer {
    private final int id;
    private final int questionId;
    private final String text;
    private final boolean isCorrect;
}