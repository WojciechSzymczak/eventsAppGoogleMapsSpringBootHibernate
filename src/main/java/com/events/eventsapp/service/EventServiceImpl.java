package com.events.eventsapp.service;

import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
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

    //TODO add checking if user deletes it's own event not other users.
    @Override
    @Transactional
    public void deleteEventById(Long eventId) {

            eventRepository.deleteEventModelById(eventId);

    }

    @Override
    public List<EventModel> getAllActualEvents() {

        List<EventModel> eventModelList = eventRepository.getAllEvents();
        List<EventModel> actualEventModelList = new LinkedList<EventModel>();

        for(EventModel e : eventModelList) {
            if(new Timestamp(System.currentTimeMillis()).compareTo(e.getBeginningDate()) <= 0) {
                actualEventModelList.add(e);
            }
        }

        return actualEventModelList;
    }

    @Override
    public List<EventModel> getActualEventsByName(String name) {

        List<EventModel> eventModelList = eventRepository.getEventsbyName(name);
        List<EventModel> actualEventModelList = new LinkedList<EventModel>();

        for(EventModel e : eventModelList) {
            if(new Timestamp(System.currentTimeMillis()).compareTo(e.getBeginningDate()) <= 0) {
                actualEventModelList.add(e);
            }
        }

        return actualEventModelList;
    }

}