package ru.otus.service;

import ru.otus.model.csv.CSVAnswer;
import ru.otus.model.csv.CSVQuestion;

import java.util.List;

public interface CSVService {

    List<CSVQuestion> getQuestions();
    List<CSVAnswer> getAnswers();
}
