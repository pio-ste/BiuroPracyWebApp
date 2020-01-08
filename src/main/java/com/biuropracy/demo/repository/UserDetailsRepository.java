package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    List<UserDetails> findByUserIdUser(Integer user);
}
