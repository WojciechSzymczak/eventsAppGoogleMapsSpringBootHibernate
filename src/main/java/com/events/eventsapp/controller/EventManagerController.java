package com.events.eventsapp.controller;

import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.service.EventService;
import com.events.eventsapp.service.EventServiceImpl;
import com.events.eventsapp.service.UserService;
import com.events.eventsapp.service.UserServiceImpl;
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
public class EventManagerController {

    @Autowired
    EventService eventService = new EventServiceImpl();

    @Autowired
    UserService userService = new UserServiceImpl();

    @RequestMapping(path = "/eventManager", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getTestView() {

        ModelAndView modelAndView = new ModelAndView("user/eventManager");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String principalName = auth.getName();

        UserModel userModel = userService.findUserByEmail(principalName);

        modelAndView.addObject("userModel",userModel);

        return modelAndView;

    }

    @RequestMapping(path = "/eventManager", method = RequestMethod.POST)
    public @ResponseBody ModelAndView postTestView(@RequestParam(name = "deleteButton", required = false) String toDelEventId) {

        ModelAndView modelAndView = new ModelAndView("user/eventManager");

        if (toDelEventId != null || !toDelEventId.trim().equals("")) {

            try {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String principalName = auth.getName();
                UserModel userModel = userService.findUserByEmail(principalName);

                Long parsedEventId = Long.parseLong(toDelEventId);

                    //This line alters UserModel object and deletes event with given id from set.
                    userModel.deleteEventById(parsedEventId);

                    //This saves altered UserModel to database so a record in join table ,,event_user'' linking users and events is deleted.
                    userService.updateUser(userModel);

                    //This line deletes record in events table.
                    eventService.deleteEventById(parsedEventId);

                    //TODO find more elegant way to deal with deleting entities in ManyToMany relationship.

                    modelAndView.addObject("userModel",userModel);

            } catch (NumberFormatException e) {
                modelAndView.addObject("errorMessage","Provided event id is invalid!");
            }

        }

        return modelAndView;

    }

}
