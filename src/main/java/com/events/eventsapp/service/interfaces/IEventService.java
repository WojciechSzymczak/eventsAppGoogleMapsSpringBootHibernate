package com.events.eventsapp.service.interfaces;

import com.events.eventsapp.model.EventModel;

import java.util.List;
import java.util.Optional;

public interface IEventService {

    EventModel findEventByName(String eventName);

    Optional<EventModel> findEventById(Long Id);

    void saveEvent(EventModel user);

    void deleteEventById(Long eventId);

    List<EventModel> getActualEvents();

    List<EventModel> getActualEventsAndSort(String sortMode);

    List<EventModel> getActualEventsByName(String name);

    List<EventModel> getActualEventsByNameAndSort(String eventName, String sortMode);

}