package ru.otus.config;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("app")
@ConstructorBinding
@AllArgsConstructor
@ToString
public class Config {
    public final String locale;
    public final String questions;
    public final String answers;
}
