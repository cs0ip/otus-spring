package ru.otus;

import lombok.val;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.service.MainService;

@ComponentScan("ru.otus")
public class Main {

    public static void main(String[] args) {
        val context = new AnnotationConfigApplicationContext(Main.class);
        val service = context.getBean(MainService.class);
        service.run();
    }
}
