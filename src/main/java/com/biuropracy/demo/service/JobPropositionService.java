package com.biuropracy.demo.service;

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

    public JobProposition createJobProposition(JobProposition jobProposition, User user, User toUser){

        jobProposition.setFromUser(user);
        jobProposition.setToUser(toUser);
        jobProposition=jobPropositionRepository.save(jobProposition);
        return jobProposition;
    }

    public JobProposition updateJobProposition(JobProposition jobProposition) {
        Optional<JobProposition> jobPropOpt = jobPropositionRepository.findById(jobProposition.getIdJobProposition());
        if(jobPropOpt.isPresent()){
            JobProposition newJobProposition =jobPropOpt.get();
            newJobProposition.setCompanyName(jobProposition.getCompanyName());
            newJobProposition.setCompanyAddress(jobProposition.getCompanyAddress());
            newJobProposition.setPositionName(jobProposition.getPositionName());
            newJobProposition.setPositionDescription(jobProposition.getPositionDescription());
            newJobProposition.setSalary(jobProposition.getSalary());
            newJobProposition.setWebLinkOffer(jobProposition.getWebLinkOffer());
            newJobProposition.setDecision(jobProposition.getDecision());

            newJobProposition = jobPropositionRepository.save(newJobProposition);
            return newJobProposition;
        } else {
            jobProposition = jobPropositionRepository.save(jobProposition);
            return jobProposition;
        }
    }
}
