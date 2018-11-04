package com.events.eventsapp.repositories;

import com.events.eventsapp.model.TimeLinePostModel;
import com.events.eventsapp.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("timeLinePostRepository")
public interface ITimeLinePostRepository extends JpaRepository<TimeLinePostModel, Long> {

    void deleteByUser(UserModel userModel);

}
