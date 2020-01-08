package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    List<Employer> findByUserIdUser(Integer user);
}
