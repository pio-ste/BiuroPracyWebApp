package com.biuropracy.demo.service;

import com.biuropracy.demo.model.User;
import com.biuropracy.demo.model.UserDetails;
import com.biuropracy.demo.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public List<UserDetails> findUserDetailByUserId(Integer user){
        return userDetailsRepository.findByUserIdUser(user);
    }

    public UserDetails getUserDetailsById(Integer id){
        Optional<UserDetails> userDetailsOpt = userDetailsRepository.findById(id);
        if (userDetailsOpt.isPresent()){
            return userDetailsOpt.get();
        } else {
            throw new RuntimeException("ID userDetails nie znalezione.");
        }
    }

    public UserDetails updateUserDetails(UserDetails userDetails){
        Optional<UserDetails> userDetailsOpt = userDetailsRepository.findById(userDetails.getIdUserDetail());
        if (userDetailsOpt.isPresent()){
            UserDetails newUserDetail = userDetailsOpt.get();
            newUserDetail.setDateBirth(userDetails.getDateBirth());
            newUserDetail.setHomeCity(userDetails.getHomeCity());
            newUserDetail.setWorkCity(userDetails.getWorkCity());
            newUserDetail.setHobby(userDetails.getHobby());
            newUserDetail.setCurrentPosition(userDetails.getCurrentPosition());
            newUserDetail.setPositionSought(userDetails.getPositionSought());
            newUserDetail.setToFind(userDetails.getToFind());

            newUserDetail = userDetailsRepository.save(newUserDetail);
            return newUserDetail;
        } else {
            userDetails = userDetailsRepository.save(userDetails);
            return userDetails;
        }
    }

    public UserDetails createUserDetails(UserDetails userDetails, User user){
        userDetails.setUser(user);
        userDetails = userDetailsRepository.save(userDetails);
        return userDetails;
    }

    public void deleteUserDetailsById(Integer id){
        Optional<UserDetails> userDetailsOpt = userDetailsRepository.findById(id);
        if (userDetailsOpt.isPresent()){
            userDetailsRepository.deleteById(id);
        } else {
            throw new RuntimeException("ID userDetails nie znalezione.");
        }
    }
}
