package com.biuropracy.demo.service;

import com.biuropracy.demo.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

    @Autowired
    EducationRepository educationRepository;
}
