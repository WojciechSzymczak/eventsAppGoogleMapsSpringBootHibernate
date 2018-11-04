package com.events.eventsapp.service.interfaces;

import com.events.eventsapp.model.UserModel;

public interface ITimeLinePostService {

    void deleteAllTimeLinePostsByUserModel(UserModel userModel);

}
