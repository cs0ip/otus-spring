package ru.otus.model.csv;

import lombok.Data;

import java.util.List;

@Data
public class CSVQNA {
    private final CSVQuestion q;
    private final List<CSVAnswer> aList;
}
