package com.events.eventsapp.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import com.events.eventsapp.model.UserDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.events.eventsapp.model.RoleModel;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.repositories.RoleRepository;
import com.events.eventsapp.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("roleRepository")
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

    /**
     * This method should be used ONLY when registering user. Otherwise it is going to overwrite the password
     * causing authentication problems.
     * @param user UserModel object to be saved in database. Must have name, email and password set.
     */
    @Override
    public void saveJustRegisteredUser(UserModel user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        RoleModel userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<RoleModel>(Arrays.asList(userRole)));

        UserDetailsModel userDetailsModel = new UserDetailsModel();
        userDetailsModel.setJoindate(new Timestamp(System.currentTimeMillis()));
        user.setUserDetailsModel(userDetailsModel);

        userRepository.save(user);

    }

    @Transactional
    @Override
    public void updateUser(UserModel user) {

        userRepository.save(user);

    }

    @Transactional
    @Override
    public void deleteUserModelByEmail(String email) {

        userRepository.deleteUserModelByEmail(email);

    }

}