package com.events.eventsapp.service;

import com.events.eventsapp.model.EventModel;

import java.util.Optional;

public interface EventService {

    public EventModel findEventByName(String eventName);

    public Optional<EventModel> findEventById(Long Id);

    public void saveEvent(EventModel user);

    void deleteEventById(Long eventId);
}