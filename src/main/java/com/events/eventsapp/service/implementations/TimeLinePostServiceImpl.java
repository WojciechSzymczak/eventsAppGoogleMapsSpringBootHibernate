package com.events.eventsapp.service.implementations;

import com.events.eventsapp.model.TimeLinePostModel;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.repositories.ITimeLinePostRepository;
import com.events.eventsapp.service.interfaces.ITimeLinePostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("timeLinePostService")
public class TimeLinePostServiceImpl implements ITimeLinePostService {

    @Qualifier("timeLinePostRepository")
    @Autowired
    ITimeLinePostRepository iTimeLinePostRepository;

    @Transactional
    @Override
    public void deleteAllTimeLinePostsByUserModel(UserModel userModel) {
        iTimeLinePostRepository.deleteByUser(userModel);
    }

    @Override
    public TimeLinePostModel getTimeLinePostModelById(Long lPostId) {
        return iTimeLinePostRepository.getOne(lPostId);
    }

    @Override
    public void deleteTimeLinePostByModel(TimeLinePostModel timeLinePostModel) {
        iTimeLinePostRepository.delete(timeLinePostModel);
    }
}
