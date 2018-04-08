package com.events.eventsapp.service;

import com.events.eventsapp.model.UserModel;

import java.util.Optional;

public interface UserService {

    public UserModel findUserByEmail(String email);

    public UserModel findUserByName(String username);

    public Optional<UserModel> findUserById(Long Id);

    public UserModel validateUser(String email, String password);

    public void saveUser(UserModel user);
}