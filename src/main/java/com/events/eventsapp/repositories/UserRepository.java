package com.events.eventsapp.repositories;

import com.events.eventsapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserModel, Long> {


//    Optional<UserModel> findByName(String user_name);
//
    Optional<UserModel> findById(Long id);

    UserModel findByName(String user_name);
    UserModel findByEmail(String user_email);
    UserModel findByEmailAndPassword(String email, String password);

}