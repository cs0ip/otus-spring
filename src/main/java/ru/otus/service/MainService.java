package ru.otus.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.otus.model.session.UserAnswer;

@Service
@RequiredArgsConstructor
public class MainService {

    private final SessionService sessionService;

    public void run() {
        val user = sessionService.runSession();
        System.out.printf(
                "Number of correct answers: %s / %s\n",
                user.getAnswers().stream().filter(UserAnswer::isCorrect).count(),
                user.getAnswers().size()
        );
    }
}
