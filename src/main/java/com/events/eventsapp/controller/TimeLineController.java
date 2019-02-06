package com.events.eventsapp.controller;

import com.events.eventsapp.model.TimeLinePostModel;
import com.events.eventsapp.model.UserModel;
import com.events.eventsapp.service.interfaces.ITimeLinePostService;
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

    @Autowired
    ITimeLinePostService iTimeLinePostService;

    @RequestMapping(path = "/timeLine", method = RequestMethod.GET)
    public @ResponseBody ModelAndView timeLineGet(@RequestParam(name = "name", required = false, defaultValue = "") String userName) {
        ModelAndView modelAndView = new ModelAndView("user/timeLine");
        modelAndView.addObject("actionPath", "/timeLine");
        modelAndView.addObject("deleteActionPath", "/timeLinePostDelete");

        UserModel userModel, principalUser;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String principalName = authentication.getName();
        List <TimeLinePostModel> timeLinePostModelList = new LinkedList<TimeLinePostModel>();

        try {
            if (userName.trim().equals("") || userName.equals(principalName)) {
                userModel = iUserService.findUserByName(principalName);
                modelAndView.addObject("canPost", "true");
                modelAndView.addObject("timeLineTitle", userModel.getName() + "'s time line:");
                timeLinePostModelList = makePostsEditable(getSortedPostsList(userModel.getTimeLinePostModels()));
            } else {
                principalUser = iUserService.findUserByName(principalName);
                userModel = iUserService.findUserByName(userName);
                if (userModel == null) {
                    throw new Exception("User \"" + userName + "\" not found.");
                }
                modelAndView.addObject("canPost", "false");
                modelAndView.addObject("timeLineTitle", userModel.getName() + "'s time line:");
                timeLinePostModelList = getSortedPostsList(userModel.getTimeLinePostModels());
                if (principalUser.isInRole("ADMIN")) {
                    timeLinePostModelList = makePostsEditable(timeLinePostModelList);
                }
            }
        }
        catch ( Exception e ) {
            modelAndView.addObject("errorMessage", "User has not be found or you don't have necessary privileges.");
        }

        modelAndView.addObject("timeLinePosts",timeLinePostModelList);

        return modelAndView;
    }

    @RequestMapping(path = "/timeLine", method = RequestMethod.POST)
    public @ResponseBody ModelAndView doPost(@RequestParam(name = "actionPath", required = true) String actionPath,
                                             @RequestParam(name = "textArea", required = true) String content) {
        ModelAndView modelAndView = new ModelAndView("user/timeLine");
        //only authenticated users can post on their time lines.
        UserModel userModel = iUserService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("deleteActionPath", "timeLinePostDelete");

        try {
            if(userModel == null) {
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
            makePostsEditable(timeLinePostModelList);

            modelAndView.addObject("timeLineTitle",userModel.getName() + "'s time line:");
            modelAndView.addObject("timeLinePosts", timeLinePostModelList);
            modelAndView.addObject("canPost", "true");
        }
        catch (Exception e) {
            modelAndView.addObject("errorMessage", e.getMessage());
        }

        //if user published a post from main time line, he will
        //remain on that line instead of being redirected to his own.
        //TODO there is a better way to solve this problem.
        if (actionPath.equals("/mainTimeLine")) {
            return mainTimeLineGet(userModel.getName()).addObject("deleteActionPath", "/mainTimeLinePostDelete");
        }

        return modelAndView;
    }

    public static List<TimeLinePostModel> getSortedPostsList(Set<TimeLinePostModel> timeLinePostModelSet) {
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

    @RequestMapping(path = "/mainTimeLine", method = RequestMethod.GET)
    public @ResponseBody ModelAndView mainTimeLineGet(@RequestParam(name = "name", required = false, defaultValue = "") String userName) {
        ModelAndView modelAndView = new ModelAndView("user/timeLine");
        modelAndView.addObject("actionPath", "/mainTimeLine");
        modelAndView.addObject("deleteActionPath", "/mainTimeLinePostDelete");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String principalName = authentication.getName();

        UserModel userModel = iUserService.findUserByName(principalName);
        List<TimeLinePostModel> timeLinePostModelList = new LinkedList<TimeLinePostModel>();

        try {
            timeLinePostModelList = iUserService.getUserMainTimeLinePosts(userModel);
            if (userModel.isInRole("ADMIN")) {
                timeLinePostModelList = makePostsEditable(timeLinePostModelList);
            }
            else {
                makeUserPostsEditable(timeLinePostModelList, userModel);
            }
            modelAndView.addObject("timeLineTitle", "Main time line:");
            modelAndView.addObject("canPost", "true");
            modelAndView.addObject("timeLinePosts", timeLinePostModelList);
        }
        catch (Exception e) {
            modelAndView.addObject("errorMessage", e.getMessage());
        }

        return modelAndView;
    }

    private List<TimeLinePostModel> makePostsEditable(List<TimeLinePostModel> sortedPostsList) {
        for (TimeLinePostModel t : sortedPostsList) {
            t.setCanDelete(true);
        }
        return sortedPostsList;
    }

    private void makeUserPostsEditable(List<TimeLinePostModel> timeLinePostModelList, UserModel userModel) {
        for (TimeLinePostModel t : timeLinePostModelList) {
            if (t.getUser().getName().equals(userModel.getName())) {
                t.setCanDelete(true);
            }
        }
    }

    @RequestMapping(path = "/mainTimeLinePostDelete", method = RequestMethod.POST)
    public @ResponseBody ModelAndView mainTimeLineDeletePost(
            @RequestParam(name = "postId", required = false) String postId ) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String principalName = authentication.getName();

        UserModel principalModel = iUserService.findUserByName(principalName);

        try {
            if(postId == null || postId.trim().equals("")) {
                throw new Exception("Wrong post id: \"" + postId + "\"");
            }

            Long LPostId = Long.parseLong(postId);
            TimeLinePostModel timeLinePostModel = iTimeLinePostService.getTimeLinePostModelById(LPostId);

            if(timeLinePostModel==null) {
                throw new Exception("No post with id: \"" + LPostId + "\" found.");
            }

            if (principalModel.isInRole(UserModel.USER_ROLE_ADMIN)) {
                iTimeLinePostService.deleteTimeLinePostByModel(timeLinePostModel);
                return mainTimeLineGet(principalName).addObject("successMessage", "ADMIN: Post was successfully deleted.");
            }
            else if (timeLinePostModel.getUser().getName().equals(principalName)) {
                iTimeLinePostService.deleteTimeLinePostByModel(timeLinePostModel);
                return mainTimeLineGet(principalName).addObject("successMessage", "Post was successfully deleted.");
            }
            else {
                return mainTimeLineGet(principalName).addObject("errorMessage", "Post cannot be deleted.");
            }
        }
        catch (Exception e) {
            return mainTimeLineGet(principalName).addObject("errorMessage", e.getMessage());
        }
    }

    @RequestMapping(path = "/timeLinePostDelete", method = RequestMethod.POST)
    public @ResponseBody ModelAndView timeLineDeletePost(
            @RequestParam(name = "postId", required = false) String postId ) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String principalName = authentication.getName();

        UserModel principalModel = iUserService.findUserByName(principalName);

        try {
            if(postId == null || postId.trim().equals("")) {
                throw new Exception("Wrong post id: \"" + postId + "\"");
            }

            Long LPostId = Long.parseLong(postId);
            TimeLinePostModel timeLinePostModel = iTimeLinePostService.getTimeLinePostModelById(LPostId);

            if(timeLinePostModel==null) {
                throw new Exception("No post with id: \"" + LPostId + "\" found.");
            }

            if (principalModel.isInRole(UserModel.USER_ROLE_ADMIN)) {
                iTimeLinePostService.deleteTimeLinePostByModel(timeLinePostModel);
                return timeLineGet(principalName).addObject("successMessage", "ADMIN: Post was successfully deleted.");
            }
            else if (timeLinePostModel.getUser().getName().equals(principalName)) {
                iTimeLinePostService.deleteTimeLinePostByModel(timeLinePostModel);
                return timeLineGet(principalName).addObject("successMessage", "Post was successfully deleted.");
            }
            else {
                return timeLineGet(principalName).addObject("errorMessage", "Post cannot be deleted.");
            }
        }
        catch (Exception e) {
            return timeLineGet(principalName).addObject("errorMessage", e.getMessage());
        }
    }
}
