package com.events.eventsapp.controller;

import com.events.eventsapp.model.EventCategoryModel;
import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.service.EventCategoryService;
import com.events.eventsapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    EventService eventService;

    @Autowired
    EventCategoryService eventCategoryService;

    @RequestMapping(path = {"/","/index","/home"}, method = RequestMethod.GET)
    public @ResponseBody ModelAndView getHomePage(@RequestParam(name = "order", required = false) String orderMode,
                                                  @RequestParam(name = "name", required = false) String eventName,
                                                  @RequestParam(name = "sortMode", required = false) String sortMode,
                                                  @RequestParam(name = "eventCategory", required = false) String eventCategory) {

        ModelAndView modelAndView = new ModelAndView("index");

        List<EventModel> eventModelList = new LinkedList<>();

        //TODO simplify parameter checking
        if(eventName == null && sortMode == null){
            eventModelList = eventService.getActualEvents();
            modelAndView.addObject("events", eventModelList);

        } else if((eventName != null && !eventName.trim().equals("")) && sortMode == null) {

            eventModelList = eventService.getActualEventsByName(eventName);
            modelAndView.addObject("events", eventModelList);

        } else if((eventName.trim().equals("") || eventName == null) && (!sortMode.trim().equals("") && sortMode != null)) {

            eventModelList = eventService.getActualEventsAndSort(sortMode);
            modelAndView.addObject("events", eventModelList);

        } else if((eventName != null && !(eventName.trim().equals(""))) && (!(sortMode.trim().equals("")) && sortMode != null)) {

            eventModelList = eventService.getActualEventsByNameAndSort(eventName,sortMode);
            modelAndView.addObject("events", eventModelList);

        }

        if(eventCategory != null) {

            EventCategoryModel eventCategoryModel = eventCategoryService.findEventCategoryModelByName(eventCategory);

            if(eventCategory.equals("nocategory")) {

                List<EventModel> categorizedEventModelList = new LinkedList<>();

                for(EventModel em : eventModelList) {
                    if (!em.getEventCategoryModels().iterator().hasNext()) {
                            categorizedEventModelList.add(em);
                    }
                }

                modelAndView.addObject("events", categorizedEventModelList);

            } else if(eventCategoryModel!= null) {

                List<EventModel> categorizedEventModelList = new LinkedList<>();

                for(EventModel em : eventModelList) {
                    if (em.getEventCategoryModels().iterator().hasNext()) {
                        if (em.getEventCategoryModels().iterator().next().getName().equals(eventCategory)) {
                            categorizedEventModelList.add(em);
                        }
                    }
                }

                modelAndView.addObject("events", categorizedEventModelList);

            }

        }

        List<EventCategoryModel> eventCategoryModelList = eventCategoryService.findEventCategoryModels();
        modelAndView.addObject("categories", eventCategoryModelList);

        return modelAndView;

    }

}
