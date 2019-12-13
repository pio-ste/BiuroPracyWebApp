package com.biuropracy.demo.service;

import com.biuropracy.demo.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;
}
