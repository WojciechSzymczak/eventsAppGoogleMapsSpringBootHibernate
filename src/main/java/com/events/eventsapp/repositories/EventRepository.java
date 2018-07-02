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
    List<EventModel> getEvents();

    @Query("select e from EventModel e where e.name like %?1%")
    List<EventModel> getEventsbyName(String name);

    @Query("select e from EventModel e where e.name like %?1% order by e.name asc")
    List<EventModel> getEventsByNameOrderByNameAsc(String name);

    @Query("select e from EventModel e where e.name like %?1% order by e.name desc")
    List<EventModel> getEventsByNameOrderByNameDesc(String name);

    @Query("select e from EventModel e where e.name like %?1% order by e.beginningDate asc")
    List<EventModel> getEventsByNameOrderByBeginningDateAsc(String name);

    @Query("select e from EventModel e where e.name like %?1% order by e.beginningDate desc")
    List<EventModel> getEventsByNameOrderByBeginningDateDesc(String name);

    @Query("select e from EventModel e order by e.name asc")
    List<EventModel> getEventsOrderByNameAsc();

    @Query("select e from EventModel e order by e.name desc")
    List<EventModel> getEventsOrderByNameDesc();

    @Query("select e from EventModel e order by e.beginningDate asc")
    List<EventModel> getEventsOrderByBeginningDateAsc();

    @Query("select e from EventModel e order by e.beginningDate desc")
    List<EventModel> getEventsOrderByBeginningDateDesc();

}