package ru.otus.util.io;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConsoleAppInput implements AppInput {
    private final Scanner in = new Scanner(System.in);

    @Override
    public String readLine() {
        return in.nextLine();
    }
}
