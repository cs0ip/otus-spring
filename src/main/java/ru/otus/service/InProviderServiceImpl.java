package ru.otus.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class InProviderServiceImpl implements InProviderService {
    @Override
    public InputStream get() {
        return System.in;
    }
}
