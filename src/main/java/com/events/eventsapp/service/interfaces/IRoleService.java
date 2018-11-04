package com.events.eventsapp.service.interfaces;

import com.events.eventsapp.model.RoleModel;

public interface IRoleService {

    RoleModel findRoleByName(String roleName);

    void saveRole(RoleModel roleModel);

}
