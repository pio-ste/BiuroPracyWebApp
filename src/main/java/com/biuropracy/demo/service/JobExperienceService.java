package com.biuropracy.demo.service;

import com.biuropracy.demo.model.JobExperience;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.JobExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobExperienceService {

    @Autowired
    JobExperienceRepository jobExperienceRepository;

    public List<JobExperience> findJobExperienceByUserId(Integer user) {
        return jobExperienceRepository.findByUserId(user);
    }

    public JobExperience getJobExperienceById(Integer id) {
        Optional<JobExperience> jobExperienceOpt = jobExperienceRepository.findById(id);
        if (jobExperienceOpt.isPresent()) {
            return jobExperienceOpt.get();
        } else {
            throw new RuntimeException("Id jobExperience nie znalezione");
        }
    }

    public JobExperience updateJobExperience(JobExperience jobExperience) {
        Optional<JobExperience> jobExperienceOpt = jobExperienceRepository.findById(jobExperience.getIdJobExperience());
        if (jobExperienceOpt.isPresent()) {
            JobExperience newJobExperience = jobExperienceOpt.get();
            newJobExperience.setPosition(jobExperience.getPosition());
            newJobExperience.setLocation(jobExperience.getLocation());
            newJobExperience.setCompanyName(jobExperience.getCompanyName());
            newJobExperience.setFromMonth(jobExperience.getFromMonth());
            newJobExperience.setFromYear(jobExperience.getFromYear());
            newJobExperience.setToMonth(jobExperience.getToMonth());
            newJobExperience.setToYear(jobExperience.getToYear());
            newJobExperience.setContinues(jobExperience.getContinues());
            newJobExperience.setDescription(jobExperience.getDescription());

            newJobExperience = jobExperienceRepository.save(newJobExperience);
            return newJobExperience;
        } else {
            jobExperience = jobExperienceRepository.save(jobExperience);
            return jobExperience;
        }
    }

    public JobExperience createJobExperience(JobExperience jobExperience, User user) {
        jobExperience.setUser(user);
        jobExperience = jobExperienceRepository.save(jobExperience);
        return jobExperience;
    }

    public void deleteJobExperienceById(Integer id) {
        Optional<JobExperience> jobExperienceOpt = jobExperienceRepository.findById(id);
        if (jobExperienceOpt.isPresent()) {
            jobExperienceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brak jobExperience o tym id");
        }
    }
}
