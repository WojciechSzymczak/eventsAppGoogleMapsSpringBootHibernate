package com.events.eventsapp.service.implementations;

import com.events.eventsapp.model.EventCategoryModel;
import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.repositories.IEventCategoryRepository;
import com.events.eventsapp.service.interfaces.IEventCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("eventCategoryService")
public class EventCategoryServiceImpl implements IEventCategoryService {

    @Qualifier("eventCategoryRepository")
    @Autowired
    IEventCategoryRepository iEventCategoryRepository;

    @Override
    public EventCategoryModel findEventCategoryModelByName(String name) {

        return iEventCategoryRepository.findByName(name);

    }

    @Override
    public void saveEventCategoryModel(EventCategoryModel eventCategoryModel) {

        iEventCategoryRepository.save(eventCategoryModel);

    }

    @Override
    public Set<EventCategoryModel> getEventCategoryModels(EventModel eventModel) {

        return iEventCategoryRepository.findEventCategoryModelsByEventModel(eventModel);

    }

    @Override
    public List<EventCategoryModel> findEventCategoryModels() {

        return iEventCategoryRepository.findEventCategoryModels();

    }


}
