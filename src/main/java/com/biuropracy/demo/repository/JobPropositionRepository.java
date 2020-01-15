package com.biuropracy.demo.repository;

import com.biuropracy.demo.DTO.JobPropositionDTO;
import com.biuropracy.demo.model.JobProposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JobPropositionRepository extends JpaRepository<JobProposition, Integer> {

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, e.idEmployer, e.companyName, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision)"
            + "from JobProposition j, Employer e "
            + "where (j.user.idUser = :id) and e.idEmployer = j.employer.idEmployer "
            + "and (j.decision = :decision or :decision is null or :decision = '')")
    List<JobPropositionDTO> getAllJPropByToUserId(@Param("id") Integer id, @Param("decision") String decision);


    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.idUser, j.positionName, u.name, u.lastName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision)"
            + "from JobProposition j, User u, Employer e "
            + "where (j.employer.idEmployer = :idEmployer) and j.user.idUser = u.idUser "
            + "and (j.decision = :decision or :decision is null or :decision = '')")
    List<JobPropositionDTO> getAllJObPropEmployer(@Param("idEmployer") Integer idEmployer, @Param("decision") String decision);

}
