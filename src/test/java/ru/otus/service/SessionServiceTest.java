package ru.otus.service;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.otus.App;
import ru.otus.util.io.AppInput;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringJUnitConfig({App.class, SessionServiceTest.TestConfiguration.class})
public class SessionServiceTest {

    @Autowired
    SessionService sessionService;

    @Test
    public void test() {
        val user = sessionService.runSession();
        System.out.println(user);
        assertThat(user.getFio()).isEqualTo("User Name");
        user.getAnswers().forEach(a -> assertThat(a.isCorrect()).isTrue());
    }

    @Configuration
    public static class TestConfiguration {
        @Bean
        @Primary
        public AppInput testAppInput() {
            return new TestAppInput();
        }

        public static class TestAppInput implements AppInput {

            private final Iterator<String> it = List.of(
                    "User Name",
                    "2",
                    "3",
                    "2",
                    "2",
                    "3"
            ).iterator();

            @Override
            public String readLine() {
                return it.next();
            }
        }
    }
}
