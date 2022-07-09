package ru.otus.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public interface InProviderService {
    InputStream get();
}
