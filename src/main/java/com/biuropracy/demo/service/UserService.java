package com.biuropracy.demo.service;


import com.biuropracy.demo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    public void saveProfileImage(Integer id, MultipartFile file);

    public void saveUser(User user);

    public User updateUser(User user);

    public boolean isUserAlreadyPresent(User user);

    public User findUserByEmail(String email);

    public User findUser(Integer id);

    public List<User> findUserById(Integer idUser);

    public List<User> getAllUsers();
}
