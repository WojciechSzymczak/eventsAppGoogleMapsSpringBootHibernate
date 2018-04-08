package com.events.eventsapp.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.events.eventsapp.model.RoleModel;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.repositories.RoleRepository;
import com.events.eventsapp.repositories.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserModel findUserByEmail(String email) {

        return userRepository.findByEmail(email);

    }

    @Override
    public UserModel findUserByName(String username) {

        return userRepository.findByName(username);

    }

    @Override
    public Optional <UserModel> findUserById(Long Id) {

        return userRepository.findById(Id);

    }

    @Override
    public UserModel validateUser(String email, String password) {

        UserModel userModel = userRepository.findByEmail(email);

        if (userModel != null) {

            if (bCryptPasswordEncoder.matches(password,userModel.getPassword()) && (userModel.getActive() != 0)) {

                return userModel;

            } else {

                return null;

            }

        } else {

            return null;

        }

    }

    @Override
    public void saveUser(UserModel user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        RoleModel userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<RoleModel>(Arrays.asList(userRole)));
        userRepository.save(user);

    }

}