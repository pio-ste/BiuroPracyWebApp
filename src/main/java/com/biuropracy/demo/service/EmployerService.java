package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Employer;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.EmployerRepository;
import com.biuropracy.demo.repository.JobOfferRepository;
import com.biuropracy.demo.repository.JobPropositionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {

    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    JobPropositionRepository jobPropositionRepository;

    @Autowired
    JobOfferRepository jobOfferRepository;
    @Autowired
    private EntityManagerService entityManagerService;

    public List<Employer> findEmployerByUserId(Integer user) {
        return employerRepository.findByUserIdUser(user);
    }

    public Employer findEmployerByUser_id(Integer id){
        return employerRepository.findEmployerByUser_IdUser(id);
    }

    public Employer findEmployer(Integer id) {
        Optional<Employer> employerOpt = employerRepository.findById(id);
        if (employerOpt.isPresent()){
            return employerOpt.get();
        } else {
            throw new RuntimeException("Id Employer nie istnieje");
        }
    }

    @Transactional
    public void saveCompanyImgImage(Integer id, MultipartFile file) {
        try {

            Employer employer = employerRepository.findById(id).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for(byte b : file.getBytes()){
                byteObjects[i++] = b;
            }
            employer.setCompanyImage(byteObjects);
            employerRepository.save(employer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Employer updateEmployer(Employer employer) {
        Optional<Employer> employerOpt = employerRepository.findById(employer.getIdEmployer());
        if (employerOpt.isPresent()){
            Employer newEmployer = employerOpt.get();
            newEmployer.setCompanyName(employer.getCompanyName());
            newEmployer.setPhoneNumber(employer.getPhoneNumber());
            newEmployer.setCompanyEmail(employer.getCompanyEmail());
            newEmployer.setWebLink(employer.getWebLink());
            newEmployer.setAddress(employer.getAddress());
            newEmployer.setDescription(employer.getDescription());

            newEmployer = employerRepository.save(newEmployer);
            return newEmployer;
        } else {
            employer = employerRepository.save(employer);
            return employer;
        }
    }

    public Employer createEmployer(Employer employer, User user) {
        employer.setUser(user);
        employer = employerRepository.save(employer);
        return employer;
    }

    public void deleteEmployerById(Integer id) throws RuntimeException{
        Optional<Employer> employerOpt = employerRepository.findById(id);
        if (employerOpt.isPresent()){
            SessionFactory sessionFactory = entityManagerService.getSessionFactory();
            Session session = sessionFactory.openSession();
            String query = "Delete from employer where id_employer ="+id;
            session.doWork(connection -> connection.prepareStatement(query).execute());
            session.close();
        } else {
            throw new RuntimeException("ID userDetails nie znalezione.");
        }
    }

}
