package ru.otus.model.session;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private final String fio;
    private final List<UserAnswer> answers;
}
