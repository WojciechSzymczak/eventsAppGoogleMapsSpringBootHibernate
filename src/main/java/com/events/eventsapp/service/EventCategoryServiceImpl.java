package com.events.eventsapp.service;

import com.events.eventsapp.model.EventCategoryModel;
import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.repositories.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("eventCategoryService")
public class EventCategoryServiceImpl implements EventCategoryService{

    @Qualifier("eventCategoryRepository")
    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Override
    public EventCategoryModel findEventCategoryModelByName(String name) {

        return eventCategoryRepository.findByName(name);

    }

    @Override
    public void saveEventCategoryModel(EventCategoryModel eventCategoryModel) {

        eventCategoryRepository.save(eventCategoryModel);

    }

    @Override
    public Set<EventCategoryModel> getEventCategoryModels(EventModel eventModel) {

        return eventCategoryRepository.findEventCategoryModelsByEventModel(eventModel);

    }


}
