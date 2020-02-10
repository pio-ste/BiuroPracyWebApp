package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Employer;
import com.biuropracy.demo.model.JobProposition;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.JobPropositionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class JobPropositionService {

    @Autowired
    JobPropositionRepository jobPropositionRepository;
    @Autowired
    private EntityManagerService entityManagerService;

    public JobProposition findByUseId(Integer id){
        return jobPropositionRepository.findByUserIdUser(id);
    }

    public JobProposition findByEmployerId(Integer id){
        return jobPropositionRepository.findByEmployerIdEmployer(id);
    }

    /**
     * zapisywanie propozycji pracy
     * @param jobProposition
     * @param user
     * @param employer
     * @return
     */
    public JobProposition createJobProposition(JobProposition jobProposition, User user, Employer employer){
        jobProposition.setUser(user);
        jobProposition.setEmployer(employer);
        jobProposition.setDecision("Nierozpatrzona");
        jobProposition=jobPropositionRepository.save(jobProposition);
        return jobProposition;
    }

    /**
     * zmiana decyzji propozycji pracy
     * @param jobProposition
     * @return
     */
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

    /**
     * aktualizowanie propozycji pracy w bazie przez admina
     * @param jobProposition
     * @return
     */
    public JobProposition updateJobPropositionAdmin(JobProposition jobProposition) {
        Optional<JobProposition> jobPropOpt = jobPropositionRepository.findById(jobProposition.getIdJobProposition());
        if(jobPropOpt.isPresent()){
            JobProposition newJobProposition =jobPropOpt.get();
            newJobProposition.setWebLinkOffer(jobProposition.getWebLinkOffer());
            newJobProposition.setSalary(jobProposition.getSalary());
            newJobProposition.setPositionName(jobProposition.getPositionName());
            newJobProposition.setPositionDescription(jobProposition.getPositionDescription());
            newJobProposition.setContactType(jobProposition.getContactType());
            newJobProposition.setDecision(jobProposition.getDecision());

            newJobProposition = jobPropositionRepository.save(newJobProposition);
            return newJobProposition;
        } else {
            jobProposition = jobPropositionRepository.save(jobProposition);
            return jobProposition;
        }
    }

    /**
     * usuwanie propozycji pracy z bazy
     * @param id
     * @throws RuntimeException
     */
    public void deleteJobProposition(Integer id) throws RuntimeException{
        Optional<JobProposition> jobPropOpt = jobPropositionRepository.findById(id);
        if(jobPropOpt.isPresent()){
            SessionFactory sessionFactory = entityManagerService.getSessionFactory();
            Session session = sessionFactory.openSession();
            String query = "Delete from job_proposition where idjob_proposition ="+id;
            session.doWork(connection -> connection.prepareStatement(query).execute());
            session.close();
        } else {
            throw new RuntimeException("Brak jobProposition o tym id");
        }
    }
}
