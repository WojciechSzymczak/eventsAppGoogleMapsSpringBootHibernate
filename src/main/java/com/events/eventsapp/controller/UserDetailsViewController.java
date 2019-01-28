package com.events.eventsapp.controller;

import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.service.implementations.UserServiceImpl;
import com.events.eventsapp.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserDetailsViewController {

    @Autowired
    IUserService iUserService = new UserServiceImpl();

    @RequestMapping(path = "/userDetailsView", method = RequestMethod.GET)
    public @ResponseBody ModelAndView doGet(@RequestParam(name = "name", required = false) String userName) {
        ModelAndView modelAndView = new ModelAndView("user/userDetailsView");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String principalName = auth.getName();
        UserModel userModel;

        if ( (userName == null || userName.trim().equals("")) ) {
            userModel = iUserService.findUserByName(principalName);
            modelAndView.addObject("edit", "true");
            modelAndView.addObject("userModel",userModel);
        }
        else {
            userModel = iUserService.findUserByName(userName);
            try {
                if ( userModel == null ) {
                    throw new Exception("User \"" + userName + "\" not found.");
                }
                modelAndView.addObject("userModel",userModel);
                if (iUserService.areFriends(userModel.getName(), principalName)) {
                    modelAndView.addObject("deleteFriend", "true");
                } else if (!userModel.getName().equals(principalName)) {
                    modelAndView.addObject("addFriend", "true");
                }
            }
            catch (Exception e) {
                modelAndView.addObject("errorMessage",e.getMessage());
            }
        }

        return modelAndView;
    }
}