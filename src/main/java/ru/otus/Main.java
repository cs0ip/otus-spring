package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.QNAService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        var service = context.getBean(QNAService.class);
        service.getQNAs().forEach(qna -> {
            System.out.printf("q: %s\n", qna.getQ().getQuestion());
            qna.getA().forEach(a -> System.out.printf("  a: %s\n", a.getAnswer()));
        });
    }
}
