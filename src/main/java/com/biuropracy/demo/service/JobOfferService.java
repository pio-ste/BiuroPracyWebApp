package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Employer;
import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.repository.JobOfferRepository;
import com.biuropracy.demo.repository.ProfilePropositionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobOfferService {

    @Autowired
    JobOfferRepository jobOfferRepository;

    @Autowired
    ProfilePropositionRepository profilePropositionRepository;

    @Autowired
    private EntityManagerService entityManagerService;

    public JobOffer findByEmployerId(Integer id){
        return jobOfferRepository.findByEmployerIdEmployer(id);
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
                newJobOffer.setContact(jobOffer.getContact());
                newJobOffer.setContractType(jobOffer.getContractType());
                newJobOffer.setDescription(jobOffer.getDescription());
                newJobOffer.setLocation(jobOffer.getLocation());
                newJobOffer.setMonthlyPay(jobOffer.getMonthlyPay());
                newJobOffer.setCategorySalary(jobOffer.getCategorySalary());
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

    public JobOffer createJobOffer(JobOffer jobOffer, Employer employer) {
        jobOffer.setEmployer(employer);
        jobOffer = jobOfferRepository.save(jobOffer);
        return jobOffer;
    }

    public void deleteJobOfferById(Integer id) throws RuntimeException{
        Optional<JobOffer> offer = jobOfferRepository.findById(id);
        if (offer.isPresent()) {
            profilePropositionRepository.deleteProfPropByJobOffer(id);
            SessionFactory sessionFactory = entityManagerService.getSessionFactory();
            Session session = sessionFactory.openSession();
            String query = "Delete from job_offer where idjob_offer ="+id;
            session.doWork(connection -> connection.prepareStatement(query).execute());
            session.close();
        } else {
            throw new RuntimeException("Brak ogłoszenia o tym id");
        }
    }
}
