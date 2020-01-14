package com.biuropracy.demo.repository;

import com.biuropracy.demo.DTO.ProfilePropositionDTO;
import com.biuropracy.demo.model.ProfileProposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProfilePropositionRepository extends JpaRepository<ProfileProposition, Integer> {

    @Query("Select new com.biuropracy.demo.DTO.ProfilePropositionDTO(p.idProfileProposition, u.idUser, j.idJobOffer, j.title, j.location, u.name, u.lastName, p.contactType, p.substantiation ,p.decision) from ProfileProposition p, User u, JobOffer j "
            +"where u.idUser = p.user.idUser and p.jobOffer.idJobOffer = :idJobOffer and j.idJobOffer = p.jobOffer.idJobOffer"
            + " and (p.decision = :decision or :decision is null or :decision = '')")
    List<ProfilePropositionDTO> getProfilePropByJobOfferId(@Param("idJobOffer") Integer idJobOffer, @Param("decision") String decision);

    @Query("Select new com.biuropracy.demo.DTO.ProfilePropositionDTO(p.idProfileProposition, j.idJobOffer, e.idEmployer, e.companyName, j.monthlyPay, j.categorySalary, j.title, j.location, p.contactType, p.substantiation, p.decision) from ProfileProposition p, User u, JobOffer j, Employer e "
            +"where u.idUser = p.user.idUser and p.user.idUser = :idUser and e.idEmployer = j.employer.idEmployer "
            +"and j.idJobOffer = p.jobOffer.idJobOffer "
            +"and (p.decision = :decision or :decision is null or :decision = '')")
    List<ProfilePropositionDTO> getAllProfilePropByUserId(@Param("idUser") Integer idUser, @Param("decision") String decision);

/*
    @Modifying
    @Transactional
    @Query("delete from ProfileProposition p where p.jobOffer.idJobOffer = :id")
    void deleteProfPropByJobOffer(@Param("id") Integer id);
*/
}
