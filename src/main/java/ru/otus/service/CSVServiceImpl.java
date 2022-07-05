package ru.otus.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVServiceImpl implements CSVService {

    @Override
    public List<Question> getQuestions() {
        return readAll("questions.csv")
                .stream()
                .map(x -> new Question(Integer.parseInt(x[0]), x[1]))
                .collect(Collectors.toList());
    }

    @Override
    public List<Answer> getAnswers() {
        return readAll("answers.csv")
                .stream()
                .map(x -> new Answer(Integer.parseInt(x[0]), x[1]))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private List<String[]> readAll(String resource) {
        try (
                var reader = Files
                        .newBufferedReader(Paths.get(ClassLoader.getSystemResource(resource).toURI()));
                var csv = new CSVReaderHeaderAwareBuilder(reader)
                        .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                        .build()
        ) {
            return csv.readAll();
        }
    }
}
