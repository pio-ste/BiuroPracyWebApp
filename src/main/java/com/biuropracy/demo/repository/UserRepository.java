package com.biuropracy.demo.repository;

import com.biuropracy.demo.DTO.UserInformationDTO;
import com.biuropracy.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("select new com.biuropracy.demo.DTO.UserInformationDTO(u.idUser, u.email, u.name, u.lastName, ui.dateBirth, ui.homeCity, ui.workCity, ui.currentPosition, ui.positionSought)"
            +"from User u, UserInformation ui where u.idUser = ui.user.idUser "
            +"and ui.toFind = 'visible' and u.status = 'ZWERYFIKOWANY' and (ui.workCity = :workCity or :workCity is null or :workCity = '')"
            +"and (ui.positionSought = :positionSought or :positionSought is null or :positionSought = '')"
            +"and (u.email = :email or :email is null or :email = '')")
    List<UserInformationDTO> getVisibleUsersFiltered(@Param("workCity") String workCity, @Param("positionSought") String positionSought, @Param("email") String email);
/*
    @Query("select new com.biuropracy.demo.model.User(u.idUser, u.email, u.name, u.lastName)"
            +"from User u where (u.workCity = :workCity or :workCity is null or :workCity = '')"
            +"and (u.positionSought = :positionSought or :positionSought is null or :positionSought = '')"
            +"and (u.email = :email or :email is null or :email = '')")
    List<User> getAllUsersAdmin(@Param("workCity") String workCity, @Param("positionSought") String positionSought, @Param("email") String email);
*/
    User findByEmail(String email);

    List<User> findUserByIdUser(Integer idUser);

    List<User> findByIdUser(User user);


}
