package com.biuropracy.demo.repository;

import com.biuropracy.demo.DTO.JobPropositionDTO;
import com.biuropracy.demo.model.JobProposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
/*
    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.idUser, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.fromUser.idUser = :id)"
            + "and  u.idUser = j.toUser.idUser and j.decision = 'Zaakceptowane'")
    List<JobPropositionDTO> getAcceptJPropByFromUserID(@Param("id") Integer id);

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.idUser, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.fromUser.idUser = :id)"
            + "and u.idUser = j.toUser.idUser and j.decision = 'Odrzucone'")
    List<JobPropositionDTO> getRejectedJPropByFromUserID(@Param("id") Integer id);

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.idUser, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.toUser.idUser = :id)"
            + "and u.idUser = j.fromUser.idUser")
    List<JobPropositionDTO> getAllUserReceivedJProp(@Param("id") Integer id);

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.idUser, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.fromUser.idUser = :id)"
            + "and u.idUser = j.toUser.idUser")
    List<JobPropositionDTO> getAllUserSendJProp(@Param("id") Integer id);
*/
}
