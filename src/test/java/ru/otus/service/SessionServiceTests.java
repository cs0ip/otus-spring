package ru.otus.service;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
public class SessionServiceTests {

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
    @ComponentScan("ru.otus")
    public static class TestConfiguration {
        @Bean
        public InProviderService inProviderService() {
            return new TestInProviderService();
        }
    }

    public static class TestInProviderService implements InProviderService {
        @Override
        public InputStream get() {
            val input = List.of(
                    "User Name",
                    "2",
                    "3",
                    "2",
                    "2",
                    "3"
            );
            return new ByteArrayInputStream(String.join("\n", input).getBytes(StandardCharsets.UTF_8));
        }
    }
}
