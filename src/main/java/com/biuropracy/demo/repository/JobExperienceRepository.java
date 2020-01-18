package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobExperienceRepository extends JpaRepository<JobExperience, Integer> {

    List<JobExperience> findByUserIdUser(Integer user);

    JobExperience findJobExperienceByUserIdUser(Integer id);
}
