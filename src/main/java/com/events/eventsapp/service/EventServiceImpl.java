package com.events.eventsapp.service;

import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("eventService")
public class EventServiceImpl implements EventService{

    @Qualifier("eventRepository")
    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventModel findEventByName(String eventName) {

        return eventRepository.findByName(eventName);

    }

    @Override
    public Optional <EventModel> findEventById(Long Id) {

        return eventRepository.findById(Id);

    }

    @Override
    public void saveEvent(EventModel event) {

        eventRepository.save(event);

    }

}