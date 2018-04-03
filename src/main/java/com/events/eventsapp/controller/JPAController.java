package com.events.eventsapp.controller;

import com.events.eventsapp.repositories.UserRepository;
import com.events.eventsapp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JPAController {

    @Autowired
    private UserRepository userRepository;

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

