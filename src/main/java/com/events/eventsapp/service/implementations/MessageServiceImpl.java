package com.events.eventsapp.service.implementations;

import com.events.eventsapp.model.MessageModel;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.repositories.IMessageRepository;
import com.events.eventsapp.service.interfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("messageService")
public class MessageServiceImpl implements IMessageService {

    @Qualifier("messageRepository")
    @Autowired
    IMessageRepository iMessageRepository;

    @Override
    public Set<MessageModel> findMessagesBySenderAndRecipient(UserModel sender, UserModel recipient) {
        return iMessageRepository.findAllBySenderAndRecipient(sender, recipient);
    }

    @Override
    public Set<MessageModel> findMessagesBySender(UserModel sender) {
        return iMessageRepository.findAllBySender(sender);
    }

    @Override
    public Set<MessageModel> findMessagesByRecipient(UserModel recipient) {
        return iMessageRepository.findAllByRecipient(recipient);
    }

    @Override
    public void sendMessage(MessageModel messageModel) {
        iMessageRepository.save(messageModel);
    }
}