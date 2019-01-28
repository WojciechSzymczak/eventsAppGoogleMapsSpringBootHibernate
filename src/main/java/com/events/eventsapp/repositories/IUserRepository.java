package com.events.eventsapp.repositories;

import com.events.eventsapp.model.TimeLinePostModel;
import com.events.eventsapp.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findById(Long id);
    UserModel findByName(String username);
    UserModel findByEmail(String email);
    void deleteUserModelByEmail(String email);

    @Query("select post from TimeLinePostModel post order by post.publishedDate desc")
    List<TimeLinePostModel> getUserMainTimeLinePosts(Long userId);
}