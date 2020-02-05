package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Education;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.EducationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    EducationRepository educationRepository;
    @Autowired
    private EntityManagerService entityManagerService;

    public List<Education> findEducationByUserId(Integer user) {
        return educationRepository.findByUserIdUser(user);
    }

    public Education findByUserId(Integer id){
        return educationRepository.findEducationByUserIdUser(id);
    }

    public Education updateEducation(Education education) {
        Optional<Education> educationOpt = educationRepository.findById(education.getIdEducation());
        if (educationOpt.isPresent()) {
            Education newEducation = educationOpt.get();
            newEducation.setSchoolName(education.getSchoolName());
            newEducation.setEducationLevel(education.getEducationLevel());
            newEducation.setSpecialization(education.getSpecialization());
            newEducation.setFromMonth(education.getFromMonth());
            newEducation.setFromYear(education.getFromYear());
            newEducation.setToMonth(education.getToMonth());
            newEducation.setToYear(education.getToYear());
            newEducation.setContinues(education.getContinues());

            newEducation = educationRepository.save(newEducation);

            return newEducation;
        } else {
            education = educationRepository.save(education);
            return education;
        }
    }

    public Education createEducation(Education education, User user) {
        education.setUser(user);
        education = educationRepository.save(education);
        return education;
    }

    public void deleteEducation(Integer id) throws RuntimeException{
        Optional<Education> educationOpt = educationRepository.findById(id);
        if (educationOpt.isPresent()) {
            SessionFactory sessionFactory = entityManagerService.getSessionFactory();
            Session session = sessionFactory.openSession();
            String query = "Delete from education where id_education ="+id;
            session.doWork(connection -> connection.prepareStatement(query).execute());
            session.close();
        } else {
            throw new RuntimeException("Id education nie znalezione");
        }
    }
}
