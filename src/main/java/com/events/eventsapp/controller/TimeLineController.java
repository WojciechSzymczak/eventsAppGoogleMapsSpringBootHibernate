package com.events.eventsapp.controller;

import com.events.eventsapp.model.TimeLinePostModel;
import com.events.eventsapp.model.UserModel;
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
public class TimeLineController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(path = "/timeLine", method = RequestMethod.GET)
    public @ResponseBody ModelAndView timeLineGet(@RequestParam(name = "name", required = false, defaultValue = "") String userName) {
        ModelAndView modelAndView = new ModelAndView("user/timeLine");

        UserModel userModel;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String principalName = authentication.getName();
        List <TimeLinePostModel> timeLinePostModelList = new LinkedList<TimeLinePostModel>();

        try {
            if (userName.trim().equals("") || userName.equals(principalName)) {
                userModel = iUserService.findUserByName(principalName);
                modelAndView.addObject("canPost", "true");
                modelAndView.addObject("timeLineTitle", userModel.getName() + "'s time line:");
                timeLinePostModelList = getSortedPostsList(userModel.getTimeLinePostModels());
            } else {
                userModel = iUserService.findUserByName(userName);
                if (userModel == null) {
                    throw new Exception("User \"" + userName + "\" not found.");
                }
                modelAndView.addObject("canPost", "false");
                modelAndView.addObject("timeLineTitle", userModel.getName() + "'s time line:");
                timeLinePostModelList = getSortedPostsList(userModel.getTimeLinePostModels());
            }
        }
        catch ( Exception e ) {
            modelAndView.addObject("errorMessage", "User has not be found or you don't have necessary privileges.");
        }

        modelAndView.addObject("timeLinePosts",timeLinePostModelList);

        return modelAndView;
    }

    @RequestMapping(path = "/timeLine", method = RequestMethod.POST)
    public @ResponseBody ModelAndView doPost(@RequestParam(name = "textArea", required = true) String content) {
        ModelAndView modelAndView = new ModelAndView("user/timeLine");
        //only authenticated users can post on their time lines.
        UserModel userModel = iUserService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());

        try {
            if( userModel == null ) {
                throw new Exception("User not found or is not authenticated.");
            }
            TimeLinePostModel timeLinePostModel = new TimeLinePostModel();
            timeLinePostModel.setPublishedDate(new Timestamp(System.currentTimeMillis()));
            timeLinePostModel.setText(content);
            timeLinePostModel.setUser(userModel);

            userModel.getTimeLinePostModels().add(timeLinePostModel);
            iUserService.updateUser(userModel);

            Set <TimeLinePostModel> timeLinePostModelSet = iUserService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()).getTimeLinePostModels();
            List<TimeLinePostModel> timeLinePostModelList = getSortedPostsList(timeLinePostModelSet);

            modelAndView.addObject("timeLineTitle",userModel.getName() + "'s time line:");
            modelAndView.addObject("timeLinePosts", timeLinePostModelList);
            modelAndView.addObject("canPost", "true");
        }
        catch ( Exception e ) {
            modelAndView.addObject("errorMessage", e.getMessage());
        }

        return modelAndView;
    }

    private static List<TimeLinePostModel> getSortedPostsList(Set<TimeLinePostModel> timeLinePostModelSet) {
        List <TimeLinePostModel> timeLinePostModelList = new LinkedList<TimeLinePostModel>();

        for ( TimeLinePostModel post : timeLinePostModelSet ) {
            timeLinePostModelList.add(post);
        }

        Collections.sort(timeLinePostModelList, new Comparator<TimeLinePostModel>() {
            @Override
            public int compare(TimeLinePostModel o1, TimeLinePostModel o2) {
                if ( o1.getPublishedDate().after(o2.getPublishedDate()) ) {
                    return -1;
                }
                else if( o1.getPublishedDate().equals(o2.getPublishedDate()) ) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
        });

        return  timeLinePostModelList;
    }
}
