package com.events.eventsapp.service;

import com.events.eventsapp.model.EventCategoryModel;
import com.events.eventsapp.model.EventModel;

import java.util.List;
import java.util.Set;

public interface EventCategoryService {

    EventCategoryModel findEventCategoryModelByName(String name);

    void saveEventCategoryModel(EventCategoryModel eventCategoryModel);

    Set<EventCategoryModel> getEventCategoryModels(EventModel eventModel);

    List<EventCategoryModel> findEventCategoryModels();

}
