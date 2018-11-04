package com.events.eventsapp.controller;

import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.service.interfaces.IEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventViewController {

    @Autowired
    IEventService iEventService;

    @RequestMapping(path = "/event", method = RequestMethod.GET)
    public @ResponseBody ModelAndView eventViewGet(@RequestParam(name = "name", required = false) String eventName) {

        ModelAndView modelAndView = new ModelAndView("eventView");

        if (eventName == null || eventName.trim().equals("")) {

            modelAndView.addObject("errorMessage", "No event specified.");
            return modelAndView;

        }

        EventModel eventModel = iEventService.findEventByName(eventName);

        if(eventModel == null) {

            modelAndView.addObject("errorMessage", "Event not found.");
            return modelAndView;
        }

        modelAndView.addObject("event", eventModel);

        return modelAndView;

    }

}
