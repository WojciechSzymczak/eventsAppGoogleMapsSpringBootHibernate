package com.events.eventsapp.service.implementations;

import com.events.eventsapp.model.RoleModel;
import com.events.eventsapp.repositories.IRoleRepository;
import com.events.eventsapp.service.interfaces.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Qualifier("roleRepository")
    @Autowired
    IRoleRepository iRoleRepository;

    @Override
    public RoleModel findRoleByName(String roleName) {
        return iRoleRepository.findByRole(roleName);
    }

    @Override
    public void saveRole(RoleModel roleModel) {
        iRoleRepository.save(roleModel);
    }

}
