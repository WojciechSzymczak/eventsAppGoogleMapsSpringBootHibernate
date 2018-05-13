package com.events.eventsapp.repositories;

import com.events.eventsapp.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<EventModel, Long> {

    Optional <EventModel> findById(Long id);
    EventModel findByName(String eventname);
    void deleteEventModelById(Long eventId);
}