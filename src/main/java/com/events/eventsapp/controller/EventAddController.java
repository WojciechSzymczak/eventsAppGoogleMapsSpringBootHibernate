package com.events.eventsapp.controller;

import com.events.eventsapp.exception.InvalidArgumentTypeException;
import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.service.interfaces.IEventService;
import com.events.eventsapp.service.interfaces.IUserService;
import com.events.eventsapp.service.implementations.EventServiceImpl;
import com.events.eventsapp.service.implementations.UserServiceImpl;
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

import java.sql.Timestamp;

@Controller
public class EventAddController {

    @Autowired
    IEventService iEventService = new EventServiceImpl();

    @Autowired
    IUserService iUserService = new UserServiceImpl();

    @RequestMapping(path = "/addEvent", method = RequestMethod.GET)
    public @ResponseBody ModelAndView addEventView() {

        return new ModelAndView("user/eventAdd");

    }

    //TODO
    //make parameter validation more precise
    @RequestMapping(path = "/addEvent", method = RequestMethod.POST)
    public @ResponseBody ModelAndView addEvent(@RequestParam(name = "eventName") String name,
                                               @RequestParam(name = "beginningDate") String date,
                                               @RequestParam(name = "beginningTime") String time,
                                               @RequestParam(name = "eventDescription") String description,
                                               @RequestParam(name = "longitude") String longitude,
                                               @RequestParam(name = "latitude") String latitude) {

        ModelAndView modelAndView = new ModelAndView("user/eventAdd");

        try {



            if (latitude == null || latitude.trim().equals("") || longitude == null || longitude.trim().equals("")) {
                throw new InvalidArgumentTypeException("Invalid longitude/latitude format. Please make sure" +
                        " the co-ordinates you are entering are in range 0 to 90 degrees for latitude" +
                        " and -180 to 180 degrees for longitude.");
            }

            Double parsedLongitude = Double.parseDouble(longitude);
            Double parsedLatitude = Double.parseDouble(latitude);

            EventModel eventModel = new EventModel();

            Timestamp timestamp = DateAndTimeUtil.getTimestamp(date, time);
            eventModel.setBeginningDate(timestamp);

            eventModel.setDescription(description);
            eventModel.setLongitude(parsedLongitude);
            eventModel.setLatitude(parsedLatitude);
            eventModel.setName(name);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            UserModel userModel = iUserService.findUserByEmail(currentPrincipalName);

            userModel.addEvent(eventModel);

            if (iEventService.findEventByName(name) != null) {
                throw new Exception("Event with name: " + name + " already exists!");
            }

            iUserService.updateUser(userModel);

            modelAndView.addObject("eventAddSucces", "Event succesfully added!");

        } catch (InvalidArgumentTypeException e) {

            modelAndView.addObject("eventAddFailure", e.getMessage());

        } catch (Exception e) {

            modelAndView.addObject("eventAddFailure", e.getMessage());

        }

        return modelAndView;

    }

}
