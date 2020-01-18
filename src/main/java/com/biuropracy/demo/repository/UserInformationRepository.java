package com.biuropracy.demo.repository;

import com.biuropracy.demo.DTO.UserInformationDTO;
import com.biuropracy.demo.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {

    @Query("select new com.biuropracy.demo.DTO.UserInformationDTO(u.idUser, u.name, u.lastName, u.email, u.userPhone, ui.idUserInformation, ui.dateBirth, ui.homeCity, ui.workCity, ui.hobby, ui.currentPosition, ui.positionSought) from User u, UserInformation ui "
            + "where u.idUser = ui.user and ui.user.idUser = :idUser" )
    List<UserInformationDTO> getUserAndUserInfoByUserId(@Param("idUser") Integer idUser);

    List<UserInformation> findByUserIdUser(Integer user);

    UserInformation findUserInformationByUserIdUser(Integer id);
}
