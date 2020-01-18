package com.biuropracy.demo.service;

import com.biuropracy.demo.model.User;
import com.biuropracy.demo.model.UserInformation;
import com.biuropracy.demo.repository.UserInformationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInformationService {

    @Autowired
    UserInformationRepository userInformationRepository;
    @Autowired
    private EntityManagerService entityManagerService;

    public List<UserInformation> findUserInfoByUserId(Integer user){
        return userInformationRepository.findByUserIdUser(user);
    }

    public UserInformation findUserInformationByUserId(Integer id) {
        return userInformationRepository.findUserInformationByUserIdUser(id);
    }

    public UserInformation getUserInfoById(Integer id){
        Optional<UserInformation> userInfoOpt = userInformationRepository.findById(id);
        if (userInfoOpt.isPresent()){
            return userInfoOpt.get();
        } else {
            throw new RuntimeException("ID userInfo nie znalezione.");
        }
    }

    public UserInformation updateUserInfo(UserInformation userInformation){
        Optional<UserInformation> userDetailsOpt = userInformationRepository.findById(userInformation.getIdUserInformation());
        if (userDetailsOpt.isPresent()){
            UserInformation newUserInfo = userDetailsOpt.get();
            newUserInfo.setDateBirth(userInformation.getDateBirth());
            newUserInfo.setHomeCity(userInformation.getHomeCity());
            newUserInfo.setWorkCity(userInformation.getWorkCity());
            newUserInfo.setHobby(userInformation.getHobby());
            newUserInfo.setCurrentPosition(userInformation.getCurrentPosition());
            newUserInfo.setPositionSought(userInformation.getPositionSought());
            newUserInfo.setToFind(userInformation.getToFind());

            newUserInfo = userInformationRepository.save(newUserInfo);
            return newUserInfo;
        } else {
            userInformation = userInformationRepository.save(userInformation);
            return userInformation;
        }
    }

    public UserInformation createUserInfo(UserInformation userInformation, User user){
        userInformation.setUser(user);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation;
    }

    public void deleteUserInfoById(Integer id){
        Optional<UserInformation> userInfoOpt = userInformationRepository.findById(id);
        if (userInfoOpt.isPresent()){
            SessionFactory sessionFactory = entityManagerService.getSessionFactory();
            Session session = sessionFactory.openSession();
            String query = "Delete from user_information where id_user_information ="+id;
            session.doWork(connection -> connection.prepareStatement(query).execute());
            //userInformationRepository.deleteById(id);
        } else {
            throw new RuntimeException("ID userDetails nie znalezione.");
        }
    }
}
