package com.events.eventsapp.controller;

import com.events.eventsapp.model.RelationshipModel;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.service.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class ContactsViewController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(path = "/contactsView", method = RequestMethod.GET)
    public @ResponseBody ModelAndView doGet() {
        ModelAndView modelAndView = new ModelAndView("user/contactsView");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String principalName = authentication.getName();
        UserModel userModel = iUserService.findUserByName(principalName);

        Set<RelationshipModel> alphaRelationshipModelSet;
        List <UserModel> userModelList = new LinkedList<>();

        try {
            if (userModel == null) {
                throw new Exception("User \"" + principalName + "\" not found.");
            }

            alphaRelationshipModelSet = userModel.getAlphaRelationshipModels();

            for (RelationshipModel rm : alphaRelationshipModelSet) {
                if (rm.isFriend() && !rm.isBlocked()) {
                    userModelList.add(rm.getBetaUserModel());
                }
            }
            modelAndView.addObject("contactsList", userModelList);
        }
        catch (Exception e) {
            modelAndView.addObject("errorMessage", e.getMessage());
        }

        return modelAndView;
    }

    @RequestMapping(path = "/contactsViewDelete", method = RequestMethod.POST)
    public @ResponseBody  ModelAndView doPostDeleteFriend(@RequestParam(name = "userName", required = false) String userName) {
        ModelAndView modelAndView = new ModelAndView("user/contactsView");

        UserModel principalUserModel = null;
        UserModel userModel = null;

        if (userName != null && !userName.trim().equals("")) {
            userModel = iUserService.findUserByName(userName);
            principalUserModel = iUserService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        }

        if(userModel == null || principalUserModel == null) {
            modelAndView.addObject("errorMessage", "Delete friend operation failed.");
        }
        else {
            iUserService.deleteContacts(userModel, principalUserModel);
            return doGet().addObject("successMessage", "User \"" + userModel.getName() + "\" deleted from contacts.");
        }

        return modelAndView;
    }
}