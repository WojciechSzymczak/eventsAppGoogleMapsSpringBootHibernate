package com.events.eventsapp.controller;

import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    EventService eventService;

    @RequestMapping(path = {"/","/index","/home"}, method = RequestMethod.GET)
    public @ResponseBody ModelAndView getHomePage(@RequestParam(name = "order", required = false) String orderMode,
                                                  @RequestParam(name = "name", required = false) String eventName) {

        ModelAndView modelAndView = new ModelAndView("index");

        if(eventName != null) {
            List<EventModel> eventModelList = eventService.getEventsByName(eventName);
            modelAndView.addObject("events", eventModelList);

        } else if(eventName == null || eventName.equals("")){
            List<EventModel> eventModelList = eventService.getAllEvents();
            modelAndView.addObject("events", eventModelList);
        }

        return modelAndView;

    }

}
