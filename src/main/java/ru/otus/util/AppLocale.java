package ru.otus.util;

import org.springframework.stereotype.Component;
import ru.otus.config.Config;

import java.util.Locale;

@Component
public class AppLocale {
    public final Locale locale;

    public AppLocale(Config cfg) {
        locale = Locale.forLanguageTag(cfg.locale);
    }
}
