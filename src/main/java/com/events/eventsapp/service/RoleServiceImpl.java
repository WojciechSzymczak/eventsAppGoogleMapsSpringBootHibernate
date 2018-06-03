package com.events.eventsapp.service;

import com.events.eventsapp.model.RoleModel;
import com.events.eventsapp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Qualifier("roleRepository")
    @Autowired
    RoleRepository roleRepository;

    @Override
    public RoleModel findRoleByName(String roleName) {
        return roleRepository.findByRole(roleName);
    }

    @Override
    public void saveRole(RoleModel roleModel) {
        roleRepository.save(roleModel);
    }

}
