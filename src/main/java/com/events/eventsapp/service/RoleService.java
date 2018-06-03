package com.events.eventsapp.service;

import com.events.eventsapp.model.RoleModel;

public interface RoleService {

    RoleModel findRoleByName(String roleName);

    void saveRole(RoleModel roleModel);

}
