package ru.otus.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.otus.model.csv.CSVQNA;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QNAServiceImpl implements QNAService {

    private final CSVService csvService;

    @Override
    public List<CSVQNA> getQNAs() {
        val questions = csvService.getQuestions();
        val answers = csvService.getAnswers();
        return questions
                .stream()
                .map(q -> new CSVQNA(q, answers.stream().filter(a -> a.getQuestionId() == q.getId()).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
