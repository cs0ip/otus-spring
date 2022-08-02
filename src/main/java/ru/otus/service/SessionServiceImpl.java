package ru.otus.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.model.csv.CSVAnswer;
import ru.otus.model.session.User;
import ru.otus.model.session.UserAnswer;
import ru.otus.util.AppLocale;
import ru.otus.util.io.AppInput;
import ru.otus.util.io.AppOutput;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final QNAService qnaService;
    private final AppInput in;
    private final AppOutput out;
    private final AppLocale locale;
    private final MessageSource ms;

    @Override
    public User runSession() {
        out.printf("%s: ", ms.getMessage("enterFIO", null, locale.locale));
        val fio = in.readLine();
        out.printf("%s\n\n", ms.getMessage("greetings", new Object[]{fio}, locale.locale));
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
                        val line = in.readLine();
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
