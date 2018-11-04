package com.events.eventsapp.service.implementations;

import com.events.eventsapp.model.EventModel;
import com.events.eventsapp.repositories.IEventRepository;
import com.events.eventsapp.service.interfaces.IEventService;
import com.events.eventsapp.service.interfaces.IEventCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import java.util.*;

@Service("eventService")
public class EventServiceImpl implements IEventService {

    @Qualifier("eventRepository")
    @Autowired
    private IEventRepository iEventRepository;

    @Autowired
    private IEventCategoryService IEventCategoryService;

    @Override
    public EventModel findEventByName(String eventName) {

        return iEventRepository.findByName(eventName);

    }

    @Override
    public Optional <EventModel> findEventById(Long Id) {

        return iEventRepository.findById(Id);

    }

    @Override
    public void saveEvent(EventModel event) {

        iEventRepository.save(event);

    }

    //TODO add checking if user deletes it's own event not other users.
    @Override
    @Transactional
    public void deleteEventById(Long eventId) {

            iEventRepository.deleteEventModelById(eventId);

    }

    @Override
    public List<EventModel> getActualEvents() {

        List<EventModel> eventModelList = iEventRepository.getEvents();
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
            eventModelList = iEventRepository.getEventsOrderByNameAsc();
        } else if(sortMode.equals("nameDesc")) {
            eventModelList = iEventRepository.getEventsOrderByNameDesc();
        } else if(sortMode.equals("timeAsc")) {
            eventModelList = iEventRepository.getEventsOrderByBeginningDateAsc();
        } else if(sortMode.equals("timeDesc")) {
            eventModelList = iEventRepository.getEventsOrderByBeginningDateDesc();
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

        List<EventModel> eventModelList = iEventRepository.getEventsbyName(name);
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
            eventModelList = iEventRepository.getEventsByNameOrderByNameAsc(eventName);
        } else if(sortMode.equals("nameDesc")) {
            eventModelList = iEventRepository.getEventsByNameOrderByNameDesc(eventName);
        } else if(sortMode.equals("timeAsc")) {
            eventModelList = iEventRepository.getEventsByNameOrderByBeginningDateAsc(eventName);
        } else if(sortMode.equals("timeDesc")) {
            eventModelList = iEventRepository.getEventsByNameOrderByBeginningDateDesc(eventName);
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