package com.biuropracy.demo.service;

import com.biuropracy.demo.model.User;
import com.biuropracy.demo.model.UserInformation;
import com.biuropracy.demo.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInformationService {

    @Autowired
    UserInformationRepository userInformationRepository;

    public List<UserInformation> findUserDetailByUserId(Integer user){
        return userInformationRepository.findByUserIdUser(user);
    }

    public UserInformation getUserDetailsById(Integer id){
        Optional<UserInformation> userDetailsOpt = userInformationRepository.findById(id);
        if (userDetailsOpt.isPresent()){
            return userDetailsOpt.get();
        } else {
            throw new RuntimeException("ID userDetails nie znalezione.");
        }
    }

    public UserInformation updateUserDetails(UserInformation userInformation){
        Optional<UserInformation> userDetailsOpt = userInformationRepository.findById(userInformation.getIdUserDetail());
        if (userDetailsOpt.isPresent()){
            UserInformation newUserDetail = userDetailsOpt.get();
            newUserDetail.setDateBirth(userInformation.getDateBirth());
            newUserDetail.setHomeCity(userInformation.getHomeCity());
            newUserDetail.setWorkCity(userInformation.getWorkCity());
            newUserDetail.setHobby(userInformation.getHobby());
            newUserDetail.setCurrentPosition(userInformation.getCurrentPosition());
            newUserDetail.setPositionSought(userInformation.getPositionSought());
            newUserDetail.setToFind(userInformation.getToFind());

            newUserDetail = userInformationRepository.save(newUserDetail);
            return newUserDetail;
        } else {
            userInformation = userInformationRepository.save(userInformation);
            return userInformation;
        }
    }

    public UserInformation createUserDetails(UserInformation userInformation, User user){
        userInformation.setUser(user);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation;
    }

    public void deleteUserDetailsById(Integer id){
        Optional<UserInformation> userDetailsOpt = userInformationRepository.findById(id);
        if (userDetailsOpt.isPresent()){
            userInformationRepository.deleteById(id);
        } else {
            throw new RuntimeException("ID userDetails nie znalezione.");
        }
    }
}
