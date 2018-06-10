package com.events.eventsapp.repositories;

import com.events.eventsapp.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<EventModel, Long> {

    Optional <EventModel> findById(Long id);
    EventModel findByName(String eventname);
    void deleteEventModelById(Long eventId);

    @Query("select e from EventModel e")
    List<EventModel> getAllEvents();

    @Query("select e from EventModel e where e.name like %?1%")
    List<EventModel> getEventsbyName(String name);
}