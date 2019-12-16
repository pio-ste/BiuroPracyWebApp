package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User findByEmail(String email);

    List<User> findUserByIdUser(Integer idUser);
}
