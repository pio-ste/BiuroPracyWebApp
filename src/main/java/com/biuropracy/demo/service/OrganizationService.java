package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Organization;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public List<Organization> findOrganizationByUserId(Integer user) {
        return organizationRepository.findByUserIdUser(user);
    }

    public Organization findByUserId(Integer id){
        return organizationRepository.findOrganizationByUserIdUser(id);
    }

    public Organization getOrganizationById(Integer id) {
        Optional<Organization> organizationOpt = organizationRepository.findById(id);

        if (organizationOpt.isPresent()) {
            return organizationOpt.get();
        } else {
            throw new RuntimeException("Id organization nie znalezione");
        }
    }

    public Organization updateOrganization(Organization organization) {
        Optional<Organization> organizationOpt = organizationRepository.findById(organization.getIdOrganization());
        if (organizationOpt.isPresent()) {
            Organization newOrganization = organizationOpt.get();
            newOrganization.setOrganizationName(organization.getOrganizationName());
            newOrganization.setLocation(organization.getLocation());
            newOrganization.setFromMonth(organization.getFromMonth());
            newOrganization.setFromYear(organization.getFromYear());
            newOrganization.setToMonth(organization.getToMonth());
            newOrganization.setToYear(organization.getToYear());
            newOrganization.setContinues(organization.getContinues());
            newOrganization.setDescription(organization.getDescription());

            newOrganization = organizationRepository.save(newOrganization);

            return newOrganization;
        } else {
            organization = organizationRepository.save(organization);
            return organization;
        }
    }

    public Organization createOrganization(Organization organization, User user) {
        organization.setUser(user);
        organization = organizationRepository.save(organization);
        return organization;
    }

    public void deleteOrganizationById(Integer id) {
        Optional<Organization> organizationOpt = organizationRepository.findById(id);
        if (organizationOpt.isPresent()) {
            organizationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brak organization o tym id");
        }
    }
}
