package ru.otus;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.service.MainService;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class App {
    public static void main(String[] args) {
        val app = SpringApplication.run(App.class, args);
        val service = app.getBean(MainService.class);
        service.run();
    }
}
