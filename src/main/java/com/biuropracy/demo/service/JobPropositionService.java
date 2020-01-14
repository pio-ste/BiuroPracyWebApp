package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Employer;
import com.biuropracy.demo.model.JobProposition;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.JobPropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class JobPropositionService {

    @Autowired
    JobPropositionRepository jobPropositionRepository;

    public JobProposition createJobProposition(JobProposition jobProposition, User user, Employer employer){
        jobProposition.setUser(user);
        jobProposition.setEmployer(employer);
        jobProposition=jobPropositionRepository.save(jobProposition);
        return jobProposition;
    }

    public JobProposition updateJobProposition(JobProposition jobProposition) {
        Optional<JobProposition> jobPropOpt = jobPropositionRepository.findById(jobProposition.getIdJobProposition());
        if(jobPropOpt.isPresent()){
            JobProposition newJobProposition =jobPropOpt.get();
            newJobProposition.setContactType(jobProposition.getContactType());
            newJobProposition.setDecision(jobProposition.getDecision());

            newJobProposition = jobPropositionRepository.save(newJobProposition);
            return newJobProposition;
        } else {
            jobProposition = jobPropositionRepository.save(jobProposition);
            return jobProposition;
        }
    }

    public void deleteJobProposition(Integer id){
        Optional<JobProposition> jobPropOpt = jobPropositionRepository.findById(id);
        if(jobPropOpt.isPresent()){
            jobPropositionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brak jobProposition o tym id");
        }
    }
}
