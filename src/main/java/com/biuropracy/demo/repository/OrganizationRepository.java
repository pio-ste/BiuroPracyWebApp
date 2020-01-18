package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    List<Organization> findByUserIdUser(Integer user);

    Organization findOrganizationByUserIdUser(Integer id);
}
