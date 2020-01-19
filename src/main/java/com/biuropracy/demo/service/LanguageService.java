package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Language;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.LanguageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    private EntityManagerService entityManagerService;

    public List<Language> findLanguageByUserId(Integer user) {
        return languageRepository.findByUserIdUser(user);
    }

    public Language findByUserId(Integer id){
        return languageRepository.findLanguageByUserIdUser(id);
    }

    public Language getLanguageById(Integer id) {
        Optional<Language> languageOpt = languageRepository.findById(id);

        if (languageOpt.isPresent()) {
            return languageOpt.get();
        } else {
            throw new RuntimeException("Id language nie znalezione");
        }
    }

    public Language updateLanguage(Language language) {
        Optional<Language> languageOpt = languageRepository.findById(language.getIdLanguage());
        if (languageOpt.isPresent()) {
            Language newLanguage = languageOpt.get();
            newLanguage.setLanguageNmae(language.getLanguageNmae());
            newLanguage.setLanguageLevel(language.getLanguageLevel());

            newLanguage = languageRepository.save(newLanguage);
            return newLanguage;
        } else {
            language = languageRepository.save(language);
            return language;
        }
    }

    public Language createLanguage(Language language, User user) {
        language.setUser(user);
        language = languageRepository.save(language);
        return language;
    }

    public void deleteLanguageById(Integer id) throws RuntimeException{
        Optional<Language> languageOpt = languageRepository.findById(id);
        if (languageOpt.isPresent()) {
            SessionFactory sessionFactory = entityManagerService.getSessionFactory();
            Session session = sessionFactory.openSession();
            String query = "Delete from language where id_language ="+id;
            session.doWork(connection -> connection.prepareStatement(query).execute());
            session.close();
        } else {
            throw new RuntimeException("Brak language o tym id");
        }
    }
}
