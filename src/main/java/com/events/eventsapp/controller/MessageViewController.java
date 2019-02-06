package com.events.eventsapp.controller;

import com.events.eventsapp.model.MessageModel;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.service.interfaces.IMessageService;
import com.events.eventsapp.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class MessageViewController {

    @Autowired
    IUserService iUserService;

    @Autowired
    IMessageService iMessageService;

    @RequestMapping(path = "/messageView", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView doGet(
            @RequestParam(name = "name", required = false, defaultValue = "") String userName) {
        ModelAndView modelAndView = new ModelAndView("user/messageView");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String principalName = authentication.getName();

        UserModel principalModel;
        UserModel userModel;

        try {
            if (userName == null || userName.trim().equals("")) {
                throw new Exception("Wrong or empty username.");
            } else if (principalName.equals(userName)) {
                throw new Exception("You can't send messages to yourself.");
            } else {
                userModel = iUserService.findUserByName(userName);
                principalModel = iUserService.findUserByName(principalName);
            }

            if (userModel == null) {
                throw new Exception("User: \"" + userName + "\" not found.");
            }

            Set<MessageModel> messageModelSet = iMessageService.findMessagesBySenderAndRecipient(principalModel, userModel);
            messageModelSet.addAll(iMessageService.findMessagesBySenderAndRecipient(userModel, principalModel));
            modelAndView.addObject("messagesList", flagOwnerList(sortMessagesSetList(messageModelSet), userModel));
            modelAndView.addObject("userName", userModel.getName());

        } catch (Exception e) {
            return modelAndView.addObject("errorMessage", e.getMessage());
        }

        return modelAndView;
    }

    List<MessageModel> sortMessagesSetList(Set<MessageModel> messageModels) {
        List<MessageModel> messageModelList = new LinkedList<MessageModel>();

        for (MessageModel msg : messageModels) {
            messageModelList.add(msg);
        }

        Collections.sort(messageModelList, new Comparator<MessageModel>() {
            @Override
            public int compare(MessageModel o1, MessageModel o2) {
                if (o1.getSendDate().after(o2.getSendDate())) {
                    return -1;
                } else if (o1.getSendDate().equals(o2.getSendDate())) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        return messageModelList;
    }

    private Object flagOwnerList(List<MessageModel> messageModels, UserModel userModel) {
        for (MessageModel m : messageModels) {
            if (m.getSender().getName().equals(userModel.getName())) {
                m.setOwner(false);
            }
            else {
                m.setOwner(true);
            }
        }
        return messageModels;
    }

    @RequestMapping(path = "/messageSend", method = RequestMethod.POST)
    public @ResponseBody ModelAndView doPost(
            @RequestParam(name = "name", required = false, defaultValue = "") String userName,
            @RequestParam(name = "textArea", required = false, defaultValue = "") String textArea) {
        ModelAndView modelAndView = new ModelAndView("user/messageView");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String principalName = authentication.getName();

        UserModel principalModel;
        UserModel userModel;

        try {
            if (textArea == null || textArea.trim().equals("")) {
                throw new Exception("Message cannot be empty.");
            }
            else if(userName.trim().equals("")) {
                throw new Exception("User not found.");
            }

            userModel = iUserService.findUserByName(userName);
            principalModel = iUserService.findUserByName(principalName);

            if(userModel == null) {
                throw new Exception("User: \"" + userName + "\" not found.");
            }

            MessageModel messageModel = new MessageModel();
            messageModel.setText(textArea);
            messageModel.setSendDate(new Timestamp(System.currentTimeMillis()));
            messageModel.setSender(principalModel);
            messageModel.setRecipient(userModel);

            iMessageService.sendMessage(messageModel);
        }
        catch (Exception e) {
            modelAndView.addObject("errorMessage", e.getMessage());
        }

        return doGet(userName);
    }
}