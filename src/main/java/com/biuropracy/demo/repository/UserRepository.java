package com.biuropracy.demo.repository;

import com.biuropracy.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select new com.biuropracy.demo.model.User(u.idUser, u.name, u.lastName, u.dateBirth, u.homeCity, u.workCity, u.currentPosition, u.positionSought, u.profileImage)"
            +"from User u where u.toFind = 'visible'")
    List<User> getVisibleUsers();

    @Query("select new com.biuropracy.demo.model.User(u.idUser, u.name, u.lastName, u.dateBirth, u.homeCity, u.workCity, u.currentPosition, u.positionSought, u.profileImage)"
            +"from User u where toFind = 'visible' and (u.workCity = :workCity or :workCity is null or :workCity = '')"
            +"and (u.positionSought = :positionSought or :positionSought is null or :positionSought = '')")
    List<User> getVisibleUsersFiltered(@Param("workCity") String workCity, @Param("positionSought") String positionSought);

    User findByEmail(String email);

    List<User> findUserByIdUser(Integer idUser);


}
