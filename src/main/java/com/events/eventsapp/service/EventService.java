package com.events.eventsapp.service;

import com.events.eventsapp.model.EventModel;

import java.util.List;
import java.util.Optional;

public interface EventService {

    EventModel findEventByName(String eventName);

    Optional<EventModel> findEventById(Long Id);

    void saveEvent(EventModel user);

    void deleteEventById(Long eventId);

    List<EventModel> getAllActualEvents();

    List<EventModel> getActualEventsByName(String name);

}