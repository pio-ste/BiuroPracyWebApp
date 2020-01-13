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
/*
    @Query("Select new com.biuropracy.demo.DTO.ProfilePropositionDTO(p.idProfileProposition, u.idUser, j.idJobOffer, j.title, j.location, j.companyName, p.user, p.jobOffer, u.name, u.lastName, u.profileImage, p.contactType, p.decision) from ProfileProposition p, User u, JobOffer j "
            +"where u.idUser = p.user.idUser and p.user.idUser = :idUser "
            + "and j.idJobOffer = p.jobOffer.idJobOffer and p.decision is null")
    List<ProfilePropositionDTO> getAllProfilePropByUserId(@Param("idUser") Integer idUser);

    @Query("Select new com.biuropracy.demo.DTO.ProfilePropositionDTO(p.idProfileProposition, u.idUser, j.idJobOffer, j.title, j.location, j.companyName, p.user, p.jobOffer, u.name, u.lastName, u.profileImage, p.contactType, p.decision) from ProfileProposition p, User u, JobOffer j "
            +"where u.idUser = p.user.idUser and p.user.idUser = :idUser "
            + "and j.idJobOffer = p.jobOffer.idJobOffer and p.decision = 'Zaakceptowane'")
    List<ProfilePropositionDTO> getAceptProfilePropByUserId(@Param("idUser") Integer idUser);

    @Query("Select new com.biuropracy.demo.DTO.ProfilePropositionDTO(p.idProfileProposition, u.idUser, j.idJobOffer, j.title, j.location, j.companyName, p.user, p.jobOffer, u.name, u.lastName, u.profileImage, p.contactType, p.decision) from ProfileProposition p, User u, JobOffer j "
            +"where u.idUser = p.user.idUser and p.user.idUser = :idUser "
            + "and j.idJobOffer = p.jobOffer.idJobOffer and p.decision = 'Odrzucone'")
    List<ProfilePropositionDTO> getRejectedProfilePropByUserId(@Param("idUser") Integer idUser);

    @Modifying
    @Transactional
    @Query("delete from ProfileProposition p where p.jobOffer.idJobOffer = :id")
    void deleteProfPropByJobOffer(@Param("id") Integer id);
*/
}
