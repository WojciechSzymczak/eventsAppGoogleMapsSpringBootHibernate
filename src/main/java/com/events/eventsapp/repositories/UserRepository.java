package com.events.eventsapp.repositories;

import com.events.eventsapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findById(Long id);
    UserModel findByName(String username);
    UserModel findByEmail(String email);
    void deleteUserModelByEmail(String email);

}