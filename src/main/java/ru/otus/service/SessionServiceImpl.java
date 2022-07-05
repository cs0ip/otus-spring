package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final QNAService qnaService;

    @Override
    public User runSession() {
        var in = new Scanner(System.in);
        var out = System.out;
        out.print("Enter your FIO: ");
        var fio = in.nextLine();
        out.printf("Hi, %s. Lets play a game\n\n", fio);
        var qna = qnaService.getQNAs();
        var userAnswers = new ArrayList<User.Answer>();
        qna.forEach(q -> {
            out.printf("Q: %s\n", q.getQ().getText());
            var answers = new HashMap<Integer, CSVService.Answer>();
            {
                var qaList = q.getAList();
                for (int i = 1; i <= qaList.size(); i++) {
                    var a = qaList.get(i - 1);
                    answers.put(i, a);
                    out.printf("  [%s]: %s\n", i, a.getText());
                }
            }
            CSVService.Answer userAnswer = null;
            {
                var isCorrectNum = false;
                while (!isCorrectNum) {
                    out.print("Enter answer number: ");
                    try {
                        var line = in.nextLine();
                        var answerNum = Integer.parseInt(line.trim());
                        var a = answers.getOrDefault(answerNum, null);
                        if (a != null) {
                            isCorrectNum = true;
                            userAnswer = a;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
            out.printf("Answer is%s correct\n\n", userAnswer.isCorrect() ? "" : " not");
            userAnswers.add(new User.Answer(q.getQ().getId(), userAnswer.getId(), userAnswer.isCorrect()));
        });
        return new User(fio, userAnswers);
    }
}
