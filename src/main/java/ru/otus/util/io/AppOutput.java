package ru.otus.util.io;

public interface AppOutput {

    AppOutput print(String str);

    AppOutput printf(String format, Object... args);

    default AppOutput printlnf(String format, Object... args) {
        return printf(format, args).print("\n");
    }
}
