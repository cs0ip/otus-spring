package ru.otus.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.otus.model.csv.CSVAnswer;
import ru.otus.model.csv.CSVQuestion;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVServiceImpl implements CSVService {

    @Override
    public List<CSVQuestion> getQuestions() {
        return readAll("questions.csv")
                .stream()
                .map(x -> new CSVQuestion(Integer.parseInt(x[0]), x[1]))
                .collect(Collectors.toList());
    }

    @Override
    public List<CSVAnswer> getAnswers() {
        return readAll("answers.csv")
                .stream()
                .map(x -> new CSVAnswer(Integer.parseInt(x[0]), Integer.parseInt(x[1]), x[2], Boolean.parseBoolean(x[3])))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private List<String[]> readAll(String resource) {
        try (
                val reader = Files
                        .newBufferedReader(Paths.get(ClassLoader.getSystemResource(resource).toURI()));
                val csv = new CSVReaderHeaderAwareBuilder(reader)
                        .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                        .build()
        ) {
            return csv.readAll();
        }
    }
}
