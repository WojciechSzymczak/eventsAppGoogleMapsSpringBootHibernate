package com.events.eventsapp.repositories;

import com.events.eventsapp.model.MessageModel;
import com.events.eventsapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository("messageRepository")
public interface IMessageRepository extends JpaRepository<MessageModel, Long> {

    Optional<MessageModel> findById(Long id);
    Set<MessageModel> findAllBySenderAndRecipient(UserModel sender, UserModel recipient);
    Set<MessageModel> findAllByRecipient(UserModel recipient);
    Set<MessageModel> findAllBySender(UserModel sender);
    Set<MessageModel> findAllBySenderOrRecipient(UserModel sender, UserModel recipient);
}