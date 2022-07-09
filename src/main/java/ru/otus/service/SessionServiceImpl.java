package ru.otus.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.otus.model.csv.CSVAnswer;
import ru.otus.model.session.User;
import ru.otus.model.session.UserAnswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final QNAService qnaService;
    private final InProviderService inProviderService;

    @Override
    public User runSession() {
        val in = new Scanner(inProviderService.get());
        val out = System.out;
        out.print("Enter your FIO: ");
        val fio = in.nextLine();
        out.printf("Hi, %s. Lets play a game\n\n", fio);
        val qna = qnaService.getQNAs();
        val userAnswers = new ArrayList<UserAnswer>();
        qna.forEach(q -> {
            out.printf("Q: %s\n", q.getQ().getText());
            val answers = new HashMap<Integer, CSVAnswer>();
            {
                val qaList = q.getAList();
                for (int i = 1; i <= qaList.size(); i++) {
                    val a = qaList.get(i - 1);
                    answers.put(i, a);
                    out.printf("  [%s]: %s\n", i, a.getText());
                }
            }
            CSVAnswer userAnswer = null;
            {
                var isCorrectNum = false;
                while (!isCorrectNum) {
                    out.print("Enter answer number: ");
                    try {
                        val line = in.nextLine();
                        val answerNum = Integer.parseInt(line.trim());
                        val a = answers.getOrDefault(answerNum, null);
                        if (a != null) {
                            isCorrectNum = true;
                            userAnswer = a;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
            out.printf("Answer is%s correct\n\n", userAnswer.isCorrect() ? "" : " not");
            userAnswers.add(new UserAnswer(q.getQ().getId(), userAnswer.getId(), userAnswer.isCorrect()));
        });
        return new User(fio, userAnswers);
    }
}
