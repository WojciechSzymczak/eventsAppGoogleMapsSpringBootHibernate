package com.events.eventsapp.service.interfaces;

import com.events.eventsapp.model.MessageModel;
import com.events.eventsapp.model.UserModel;

import java.util.Set;

public interface IMessageService {

    public Set<MessageModel> findMessagesBySenderOrRecipient(UserModel sender, UserModel recipient);
    public Set<MessageModel> findMessagesBySender(UserModel sender);
    public Set<MessageModel> findMessagesByRecipient(UserModel recipient);
    void sendMessage(MessageModel messageModel);
}