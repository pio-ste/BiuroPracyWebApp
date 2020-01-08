package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {

    List<UserInformation> findByUserIdUser(Integer user);
}
