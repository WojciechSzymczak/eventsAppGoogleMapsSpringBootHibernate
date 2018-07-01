package com.events.eventsapp.repositories;

import com.events.eventsapp.model.EventCategoryModel;
import com.events.eventsapp.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("eventCategoryRepository")
public interface EventCategoryRepository extends JpaRepository<EventCategoryModel, Long> {

    EventCategoryModel findByName(String name);

    Set<EventCategoryModel> findEventCategoryModelsByEventModel(EventModel eventModel);

}
