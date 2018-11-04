package com.events.eventsapp.service.interfaces;

import com.events.eventsapp.model.UserModel;

import java.util.Optional;

public interface IUserService {

    public UserModel findUserByEmail(String email);
    public UserModel findUserByName(String username);
    public Optional<UserModel> findUserById(Long Id);
    public UserModel validateUser(String email, String password);
    public void saveJustRegisteredUser(UserModel user);
    public void updateUser(UserModel user);
    void deleteUserModelByEmail(String email);

}