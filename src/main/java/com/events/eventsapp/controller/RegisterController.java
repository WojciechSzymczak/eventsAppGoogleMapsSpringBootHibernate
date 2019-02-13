package com.events.eventsapp.controller;


import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.service.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public @ResponseBody ModelAndView register() {

        return new ModelAndView("register");

    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public @ResponseBody ModelAndView register(
                         @RequestParam(name = "email", required = false) String email,
                         @RequestParam(name = "username", required = false) String username,
                         @RequestParam(name = "password", required = false) String password,
                         @RequestParam(name = "passwordRepeated", required = false) String passwordRepeated) {


        ModelAndView mav = new ModelAndView("register");

        try {

            if (password.toString().length() <8) {
                throw new Exception("Password must have minimum 8 characters.");
            }

            if (!password.toString().equals(passwordRepeated.toString())) {
                throw new Exception("Re-entered password doesn't match.");
            }

            if (iUserService.findUserByEmail(email) != null) {
                throw new Exception("An e-mail given is already in database.");
            }

            if (iUserService.findUserByName(username) != null) {
                throw new Exception("A username given is already in database.");
            }

            UserModel userModel = new UserModel();

            userModel.setEmail(email);
            userModel.setName(username);
            userModel.setPassword(password);

            iUserService.saveJustRegisteredUser(userModel);

            mav = new ModelAndView("index");
            mav.addObject("message",
                          "Your account was succesfully created! You can now log in.");

        } catch(Exception e) {

            mav.addObject("message", e.getMessage());

        } finally {

            return mav;

        }

    }

}
