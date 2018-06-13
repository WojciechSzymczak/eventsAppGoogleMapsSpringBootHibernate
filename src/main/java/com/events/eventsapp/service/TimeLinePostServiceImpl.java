package com.events.eventsapp.service;

import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.repositories.TimeLinePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("timeLinePostService")
public class TimeLinePostServiceImpl implements TimeLinePostService{

    @Qualifier("timeLinePostRepository")
    @Autowired
    TimeLinePostRepository timeLinePostRepository;

    @Transactional
    @Override
    public void deleteAllTimeLinePostsByUserModel(UserModel userModel) {
        timeLinePostRepository.deleteByUser(userModel);
    }

}
