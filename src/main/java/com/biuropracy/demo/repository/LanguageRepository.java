package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    List<Language> findByUserId(Integer user);
}
