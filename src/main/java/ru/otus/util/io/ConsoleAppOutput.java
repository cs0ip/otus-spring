package ru.otus.util.io;

import org.springframework.stereotype.Component;

@Component
public class ConsoleAppOutput implements AppOutput {

    @Override
    public AppOutput print(String str) {
        System.out.print(str);
        return this;
    }

    @Override
    public AppOutput printf(String format, Object... args) {
        System.out.printf(format, args);
        return this;
    }
}
