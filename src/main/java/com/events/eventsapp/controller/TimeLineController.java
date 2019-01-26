package com.events.eventsapp.controller;

import com.events.eventsapp.model.TimeLinePostModel;
import com.events.eventsapp.model.UserModel;
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

import java.util.Set;

@Controller
public class TimeLineController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(path = "/timeline", method = RequestMethod.GET)
    public @ResponseBody ModelAndView timeLineGet(@RequestParam(required = false) String userName) {

        ModelAndView modelAndView = new ModelAndView("user/timeLine");

        UserModel userModel = iUserService.findUserByName(userName);

        if(userModel == null) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userModel = iUserService.findUserByName(auth.getName());

        }

        if(userModel != null) {

            Set<TimeLinePostModel> timeLinePostModelSet = userModel.getTimeLinePostModels();
            modelAndView.addObject("userName",userModel.getName());
            modelAndView.addObject("timeLinePosts",timeLinePostModelSet);

        } else {

            modelAndView.addObject("errorMessage", "User has not be found or you don't have " +
                    "necessary privileges.");

        }

        return modelAndView;

    }
}
