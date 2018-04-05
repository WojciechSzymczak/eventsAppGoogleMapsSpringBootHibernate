package com.events.eventsapp.controller;

import com.events.eventsapp.repositories.UserRepository;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.service.UserService;
import com.events.eventsapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JPAController {

    //@Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    //@Qualifier("userService")
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(path="/encode")
        public String encode() {

            String haselko = bCryptPasswordEncoder.encode("haslo123").toString();

        //UserService userService = new UserServiceImpl();
        UserModel UserModel1 = userServiceImpl.findUserByEmail("Kowalski@wp.pl");

//
//        UserService userService = userServiceImpl;
//
//        Optional <UserModel> optUserModel = userService.findUserById(new Long(1));
        //UserModel userModel = userServiceImpl.findUserByEmail("kowal");
            return  haselko + "\n decoded: " + bCryptPasswordEncoder.matches("haslo123", haselko) + "\n" +
                    UserModel1.getEmail();

        }

//        @GetMapping(path="/allusers")
//        public List <UserModel> retrieveAllUsers() {
//            return userRepository.findAll();
//        }
//
//        @GetMapping(path="/kowal")
//        public Optional <UserModel> retrieveKowal() {
//
//            return userRepository.findByName("kowal");
//        }
//
//        @GetMapping(path="/nowak")
//        public Optional <UserModel> retrieveNowak() {
//
//            return userRepository.findByEmail("Nowak@wp.pl");
//        }

    }

