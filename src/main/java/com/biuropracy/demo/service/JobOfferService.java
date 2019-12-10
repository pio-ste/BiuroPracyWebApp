package com.biuropracy.demo.service;

import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobOfferService {

    @Autowired
    JobOfferRepository jobOfferRepository;

    public List<JobOffer> getAllJobOffers(){
        return jobOfferRepository.findAll();
    }

    public JobOffer getJobOfferById(Integer id) {
        Optional<JobOffer> offer = jobOfferRepository.findById(id);

        if (offer.isPresent()) {
            return offer.get();
        } else {
            throw new RuntimeException("Id oferty nie znalezione");
        }
    }

    public JobOffer updateJobffer(JobOffer jobOffer){
            Optional<JobOffer> offer = jobOfferRepository.findById(jobOffer.getIdJobOffer());
            if (offer.isPresent()) {
                JobOffer newJobOffer = offer.get();
                newJobOffer.setCategory(jobOffer.getCategory());
                newJobOffer.setCompanyName(jobOffer.getCompanyName());
                newJobOffer.setContact(jobOffer.getContact());
                newJobOffer.setContractType(jobOffer.getContractType());
                newJobOffer.setDescription(jobOffer.getDescription());
                newJobOffer.setLocation(jobOffer.getLocation());
                newJobOffer.setMonthlyPay(jobOffer.getMonthlyPay());
                newJobOffer.setPositionLevel(jobOffer.getPositionLevel());
                newJobOffer.setTitle(jobOffer.getTitle());
                newJobOffer.setWorkingTime(jobOffer.getWorkingTime());

                newJobOffer = jobOfferRepository.save(newJobOffer);

                return newJobOffer;
            } else {
                jobOffer = jobOfferRepository.save(jobOffer);
                return jobOffer;
            }
    }

    public JobOffer createJobOffer(JobOffer jobOffer, User user) {
        jobOffer.setUser(user);
        jobOffer = jobOfferRepository.save(jobOffer);
        return jobOffer;
    }

    public void deleteJobOfferById(Integer id) {
        Optional<JobOffer> offer = jobOfferRepository.findById(id);
        if (offer.isPresent()) {
            jobOfferRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brak ogłoszenia o tym id");
        }
    }
}
