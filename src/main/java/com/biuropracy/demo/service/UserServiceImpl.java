package com.biuropracy.demo.service;

import com.biuropracy.demo.model.Role;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.RoleRepository;
import com.biuropracy.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void saveProfileImage(Integer id, MultipartFile file) {
        try {
            User user = userRepository.findById(id).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for(byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            user.setProfileImage(byteObjects);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user){
        Optional<User> userOpt = userRepository.findById(user.getIdUser());
        if (userOpt.isPresent()) {
            User newUser = userOpt.get();
            newUser.setName(user.getName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setDateBirth(user.getDateBirth());
            newUser.setHomeCity(user.getHomeCity());
            newUser.setWorkCity(user.getWorkCity());
            newUser.setHobby(user.getHobby());
            newUser.setCurrentPosition(user.getCurrentPosition());
            newUser.setToFind(user.getToFind());

            newUser = userRepository.save(newUser);
            return newUser;
        } else {
            user = userRepository.save(user);
            return user;
        }
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {
        return false;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUser(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()){
            return userOpt.get();
        } else {
            throw new RuntimeException("Id użytkownika nie istnieje");
        }
    }


    @Override
    public List<User> findUserById(Integer idUser) {
        return userRepository.findUserByIdUser(idUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
