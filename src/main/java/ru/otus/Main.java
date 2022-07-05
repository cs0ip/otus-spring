package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.model.User;
import ru.otus.service.SessionService;

@ComponentScan("ru.otus")
public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class);
        var service = context.getBean(SessionService.class);
        var user = service.runSession();
        System.out.printf(
                "Number of correct answers: %s / %s\n",
                user.getAnswers().stream().filter(User.Answer::isCorrect).count(),
                user.getAnswers().size()
        );
    }
}
