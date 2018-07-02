package com.events.eventsapp.service;

import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service("eventService")
public class EventServiceImpl implements EventService{

    @Qualifier("eventRepository")
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryService eventCategoryService;

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
    public List<EventModel> getActualEvents() {

        List<EventModel> eventModelList = eventRepository.getEvents();
        List<EventModel> actualEventModelList = new LinkedList<EventModel>();

        for(EventModel e : eventModelList) {
            if(new Timestamp(System.currentTimeMillis()).compareTo(e.getBeginningDate()) <= 0) {

                actualEventModelList.add(e);

            }
        }

        return actualEventModelList;
    }

    @Override
    public List<EventModel> getActualEventsAndSort(String sortMode) {

        List<EventModel> eventModelList = new LinkedList<EventModel>();

        if(sortMode.equals("nameAsc")) {
            eventModelList = eventRepository.getEventsOrderByNameAsc();
        } else if(sortMode.equals("nameDesc")) {
            eventModelList = eventRepository.getEventsOrderByNameDesc();
        } else if(sortMode.equals("timeAsc")) {
            eventModelList = eventRepository.getEventsOrderByBeginningDateAsc();
        } else if(sortMode.equals("timeDesc")) {
            eventModelList = eventRepository.getEventsOrderByBeginningDateDesc();
        }


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

    @Override
    public List<EventModel> getActualEventsByNameAndSort(String eventName, String sortMode) {

        List<EventModel> eventModelList = new LinkedList<>();

        if(sortMode.equals("nameAsc")) {
            eventModelList = eventRepository.getEventsByNameOrderByNameAsc(eventName);
        } else if(sortMode.equals("nameDesc")) {
            eventModelList = eventRepository.getEventsByNameOrderByNameDesc(eventName);
        } else if(sortMode.equals("timeAsc")) {
            eventModelList = eventRepository.getEventsByNameOrderByBeginningDateAsc(eventName);
        } else if(sortMode.equals("timeDesc")) {
            eventModelList = eventRepository.getEventsByNameOrderByBeginningDateDesc(eventName);
        }

        List<EventModel> actualEventModelList = new LinkedList<EventModel>();

        for(EventModel e : eventModelList) {
            if(new Timestamp(System.currentTimeMillis()).compareTo(e.getBeginningDate()) <= 0) {

                actualEventModelList.add(e);

            }
        }

        return actualEventModelList;

    }

}