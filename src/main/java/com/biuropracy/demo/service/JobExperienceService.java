package com.biuropracy.demo.service;

import com.biuropracy.demo.repository.JobExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobExperienceService {

    @Autowired
    JobExperienceRepository jobExperienceRepository;
}
