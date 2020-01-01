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

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.toUser = :id)"
            + "and u.idUser = j.toUser")
    List<JobPropositionDTO> getAllJPropByToUserId(@Param("id") Integer id);

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.toUser = :id)"
            + "and  u.idUser = j.toUser and j.decision = 'accept'")
    List<JobPropositionDTO> getAcceptJPropByToUserID(@Param("id") Integer id);

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.toUser = :id)"
            + "and u.idUser = j.toUser and j.decision = 'rejected'")
    List<JobPropositionDTO> getRejectedJPropByToUserID(@Param("id") Integer id);

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.fromUser = :id)"
            + "and u.idUser = j.toUser")
    List<JobPropositionDTO> getAllJPropByFromUserId(@Param("id") Integer id);

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.fromUser = :id)"
            + "and  u.idUser = j.toUser and j.decision = 'accept'")
    List<JobPropositionDTO> getAcceptJPropByFromUserID(@Param("id") Integer id);

    @Query("Select new com.biuropracy.demo.DTO.JobPropositionDTO(j.idJobProposition, u.name, u.lastName, j.companyName, j.companyAddress, j.positionName, j.positionDescription, j.salary, j.webLinkOffer, j.contactType, j.decision, j.fromUser, j.toUser)"
            + "from JobProposition j, User u "
            + "where (j.fromUser = :id)"
            + "and u.idUser = j.toUser and j.decision = 'rejected'")
    List<JobPropositionDTO> getRejectedJPropByFromUserID(@Param("id") Integer id);

}
