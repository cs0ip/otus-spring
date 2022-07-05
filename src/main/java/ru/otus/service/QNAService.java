package ru.otus.service;

import lombok.Data;

import java.util.List;

public interface QNAService {

    List<QNA> getQNAs();

    @Data
    class QNA {
        private final CSVService.Question q;
        private final List<CSVService.Answer> aList;
    }
}
