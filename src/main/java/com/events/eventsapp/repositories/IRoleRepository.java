package com.events.eventsapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.events.eventsapp.model.RoleModel;

@Repository("roleRepository")
//@Repository
public interface IRoleRepository extends JpaRepository<RoleModel, Integer>{

    RoleModel findByRole(String role);

}