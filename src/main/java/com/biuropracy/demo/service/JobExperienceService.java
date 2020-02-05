package com.biuropracy.demo.service;

import com.biuropracy.demo.model.JobExperience;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.JobExperienceRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobExperienceService {

    @Autowired
    JobExperienceRepository jobExperienceRepository;
    @Autowired
    private EntityManagerService entityManagerService;

    public List<JobExperience> findJobExperienceByUserId(Integer user) {
        return jobExperienceRepository.findByUserIdUser(user);
    }

    public JobExperience findByUserId(Integer id){
        return jobExperienceRepository.findJobExperienceByUserIdUser(id);
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

    public void deleteJobExperienceById(Integer id) throws RuntimeException{
        Optional<JobExperience> jobExperienceOpt = jobExperienceRepository.findById(id);
        if (jobExperienceOpt.isPresent()) {
            SessionFactory sessionFactory = entityManagerService.getSessionFactory();
            Session session = sessionFactory.openSession();
            String query = "Delete from job_experience where id_job_experience ="+id;
            session.doWork(connection -> connection.prepareStatement(query).execute());
            session.close();
        } else {
            throw new RuntimeException("Brak jobExperience o tym id");
        }
    }
}
