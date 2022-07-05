package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.service.QNAService;

@ComponentScan("ru.otus")
public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);
        var service = context.getBean(QNAService.class);
        service.getQNAs().forEach(qna -> {
            System.out.printf("q: %s\n", qna.getQ().getQuestion());
            qna.getA().forEach(a -> System.out.printf("  a: %s\n", a.getAnswer()));
        });
    }
}
