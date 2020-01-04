package com.biuropracy.demo.repository;

import com.biuropracy.demo.DTO.ProfilePropositionDTO;
import com.biuropracy.demo.model.ProfileProposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfilePropositionRepository extends JpaRepository<ProfileProposition, Integer> {

    @Query("Select new com.biuropracy.demo.DTO.ProfilePropositionDTO(p.idProfileProposition, p.idUser, p.idJobOffer, u.name, u.lastName, u.profileImage, p.contactType, p.decision) from ProfileProposition p, User u "
            +"where u.idUser = p.idUser.idUser and p.idJobOffer.idJobOffer = :idJobOffer")
    List<ProfilePropositionDTO> getprofilePropByJobOfferId(@Param("idJobOffer") Integer idJobOffer);

}
