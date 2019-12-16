package com.biuropracy.demo.service;


import com.biuropracy.demo.model.User;

import java.util.List;

public interface UserService {

    public void saveUser(User user);

    public boolean isUserAlreadyPresent(User user);

    public User findUserByEmail(String email);

    public List<User> findUserById(Integer idUser);

    public List<User> getAllUsers();
}
