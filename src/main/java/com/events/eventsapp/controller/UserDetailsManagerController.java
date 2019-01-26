package com.events.eventsapp.controller;

import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.service.implementations.UserServiceImpl;
import com.events.eventsapp.service.interfaces.IUserService;
import com.events.eventsapp.util.DateAndTimeUtil;
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
public class UserDetailsManagerController {

    @Autowired
    IUserService iUserService = new UserServiceImpl();


    @RequestMapping(path = "/userDetailsManager", method = RequestMethod.GET)
    public @ResponseBody ModelAndView doGet(@RequestParam(name = "name", required = false) String userName) {
        ModelAndView modelAndView = new ModelAndView("user/userDetailsManager");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String principalName = auth.getName();
        UserModel userModel;

        if ( principalName.equals(userName)) {
            modelAndView.addObject("edit", "true");
        }

        if ( (userName == null || userName.trim().equals("")) ) {
            userModel = iUserService.findUserByName(principalName);
            modelAndView.addObject("edit", "true");
        }
        else {
            if ( auth.getAuthorities().contains("ADMIN") ) {
                userModel = iUserService.findUserByName(userName);
            }
            else if ( principalName.equals(userName) ) {
                userModel = iUserService.findUserByName(principalName);
            }
            else {
                userModel = null;
            }
        }

        try {
            if ( userModel == null ) {
                throw new Exception("User \"" + userName + "\" not found.");
            }
            modelAndView.addObject("userModel",userModel);
        }
        catch (Exception e) {
            modelAndView.addObject("errorMessage",e.getMessage());
        }

        return modelAndView;
    }

    /**
     *
     * @param userName
     * @param firstName
     * @param lastName
     * @param birthDate
     * @return
     */
    @RequestMapping(path = "/userDetailsManager", method = RequestMethod.POST)
    public @ResponseBody ModelAndView doPost(@RequestParam(name = "userName", required = false) String userName,
                                             @RequestParam(name = "firstName", required = false) String firstName,
                                             @RequestParam(name = "lastName", required = false) String lastName,
                                             @RequestParam(name = "birthDate", required = false) String birthDate) {
        ModelAndView modelAndView = new ModelAndView("user/userDetailsView");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String principalName = auth.getName();
        UserModel userModel;

        //We make sure that only admins can alter other users personal information.
        if( auth.getAuthorities().contains("ADMIN") ) {
            userModel = iUserService.findUserByName(userName);
        }
        else {
            userModel = iUserService.findUserByName(principalName);
        }

        try {
            userModel.getUserDetailsModel().setFirstname(firstName);
            userModel.getUserDetailsModel().setLastname(lastName);
            userModel.getUserDetailsModel().setBirthdate(DateAndTimeUtil.getTimestamp(birthDate, "00:00 AM"));
            iUserService.updateUser(userModel);
            modelAndView.addObject("userModel", userModel);
            modelAndView.addObject("edit", "true");
            modelAndView.addObject("successMessage", "User: \"" + userModel.getName() + "\" details were successfully updated.");
        }
        catch (Exception e) {
            modelAndView = new ModelAndView("user/userDetailsManager");
            modelAndView.addObject("userModel", userModel);
            modelAndView.addObject("errorMessage", e.getMessage());
        }

        return modelAndView;
    }
}