package com.biuropracy.demo.repository;

import com.biuropracy.demo.DTO.JobOfferDTO;
import com.biuropracy.demo.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Integer> {

    @Query("Select new com.biuropracy.demo.DTO.JobOfferDTO(j.title, j.location, j.category,j.monthlyPay, u.email) from JobOffer j, User u where u.idUser = j.user")
    public List<JobOfferDTO> getJobOfferDTO();

    @Query("Select new com.biuropracy.demo.DTO.JobOfferDTO(j.title, j.location, j.category,j.monthlyPay, u.email) from JobOffer j, User u "
            + "where u.idUser = j.user and (j.category = :category or :category is null or :category = '')"
            + "and (j.location = :location or :location is null or :location = '')"
            + "and (j.contractType = :contractType or :contractType is null or :contractType = '')")
    List<JobOfferDTO> getJobOfferFiltered(@Param("category") String category, @Param("location") String location, @Param("contractType") String contractType);
}
