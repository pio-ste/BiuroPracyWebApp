package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

    List<Education> findByUserIdUser(Integer user);

    Education findEducationByUserIdUser(Integer id);
}
