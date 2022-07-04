package ru.otus.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QNAServiceImpl implements QNAService {

    private final CSVService csvService;

    @Override
    public List<QNA> getQNAs() {
        var questions = csvService.getQuestions();
        var answers = csvService.getAnswers();
        return questions
                .stream()
                .map(q -> new QNA(q, answers.stream().filter(a -> a.getQuestionId() == q.getId()).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
