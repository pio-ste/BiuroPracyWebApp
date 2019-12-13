package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobExperienceRepository extends JpaRepository<JobExperience, Integer> {
}
