package ru.otus.service;

import ru.otus.model.csv.CSVQNA;

import java.util.List;

public interface QNAService {

    List<CSVQNA> getQNAs();
}
