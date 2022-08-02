package ru.otus.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.Config;
import ru.otus.model.csv.CSVAnswer;
import ru.otus.model.csv.CSVQuestion;
import ru.otus.util.AppLocale;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CSVServiceImpl implements CSVService {

    private final Config cfg;
    private final AppLocale locale;
    private final MessageSource ms;

    @Override
    public List<CSVQuestion> getQuestions() {
        return readAll(cfg.questions)
                .stream()
                .map(x -> new CSVQuestion(Integer.parseInt(x[0]), ms.getMessage(x[1], null, locale.locale)))
                .collect(Collectors.toList());
    }

    @Override
    public List<CSVAnswer> getAnswers() {
        return readAll(cfg.answers)
                .stream()
                .map(x ->
                        new CSVAnswer(
                                Integer.parseInt(x[0]),
                                Integer.parseInt(x[1]),
                                ms.getMessage(x[2], null, locale.locale),
                                Boolean.parseBoolean(x[3])
                        )
                )
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
