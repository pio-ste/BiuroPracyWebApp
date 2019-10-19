package com.biuropracy.demo.service;


import com.biuropracy.demo.model.User;

public interface UserService {

    public void saveUser(User user);

    public boolean isUserAlreadyPresent(User user);
}
