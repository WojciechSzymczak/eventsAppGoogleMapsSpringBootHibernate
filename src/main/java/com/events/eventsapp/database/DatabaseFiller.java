package com.events.eventsapp.database;

import com.events.eventsapp.model.*;
import com.events.eventsapp.service.*;
import com.events.eventsapp.util.DateAndTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides several method for filling database with initial and/or initial data.
 * For production use productionFill() method.
 * For testing and developing purposes use testingFill() method.
 */
@Component
public class DatabaseFiller implements ApplicationRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    TimeLinePostService timeLinePostService;

    @Autowired
    EventCategoryService eventCategoryService;

    /**
     * Method fills database with user's roles and event's categories.
     */
    public void productionFill() {

        //Adding roles to database:
        RoleModel userRole = new RoleModel();
        RoleModel adminRole = new RoleModel();

        userRole.setRole("USER");
        userRole.setId(1);

        adminRole.setRole("ADMIN");
        adminRole.setId(2);

        roleService.saveRole(userRole);
        roleService.saveRole(adminRole);

        //Adding event's categories:
        EventCategoryModel eventCategoryModel1 = new EventCategoryModel();
        EventCategoryModel eventCategoryModel2 = new EventCategoryModel();
        EventCategoryModel eventCategoryModel3 = new EventCategoryModel();

        eventCategoryModel1.setName("Parties & social life");
        eventCategoryModel2.setName("Automotive");
        eventCategoryModel3.setName("Concert");

        eventCategoryService.saveEventCategoryModel(eventCategoryModel1);
        eventCategoryService.saveEventCategoryModel(eventCategoryModel2);
        eventCategoryService.saveEventCategoryModel(eventCategoryModel3);

    }

    /**
     * Method calls productionFill() method then populates database with example data.
     */
    public void testingFill() {

        productionFill();

        UserModel testAdminModel = new UserModel();
        UserModel testUser1Model = new UserModel();
        UserModel testUser2Model = new UserModel();

        //Creating admin account:
        testAdminModel.setEmail("Kowalski@wp.pl");
        testAdminModel.setPassword("haslo123");
        testAdminModel.setName("kowal");
        userService.saveJustRegisteredUser(testAdminModel);

        //Changing from USER role to ADMIN:
        testAdminModel.setRoles(new HashSet<RoleModel>(Arrays.asList(roleService.findRoleByName("ADMIN"))));
        userService.updateUser(testAdminModel);

        //Creating admin's events:
        EventModel eventModel1 = new EventModel();
        EventModel eventModel2 = new EventModel();

        eventModel1.setName("Admin's party.");
        eventModel1.setDescription("Admin LAN party.");
        eventModel1.setBeginningDate(DateAndTimeUtil.getTimestamp("2018-10-09", "02:03 PM"));
        eventModel1.setLatitude(51.76147975278799);
        eventModel1.setLongitude(19.46422304609382);

        eventModel2.setName("Admin's birthday.");
        eventModel2.setDescription("Admin will be 32.");
        eventModel2.setBeginningDate(DateAndTimeUtil.getTimestamp("2018-09-12", "11:00 AM"));
        eventModel2.setLatitude(51.76278799147975);
        eventModel2.setLongitude(19.44609382642230);

        Set <EventModel> adminEventsSet = new HashSet<EventModel>();
        adminEventsSet.add(eventModel1);
        adminEventsSet.add(eventModel2);
        testAdminModel.setEvents(adminEventsSet);
        userService.updateUser(testAdminModel);



        //Creating user's accounts:
        testUser1Model.setEmail("Nowak@wp.pl");
        testUser1Model.setPassword("haslo123");
        testUser1Model.setName("nowak");
        userService.saveJustRegisteredUser(testUser1Model);

        testUser2Model.setEmail("Bogusz@wp.pl");
        testUser2Model.setPassword("haslo123");
        testUser2Model.setName("bogusz");
        userService.saveJustRegisteredUser(testUser2Model);

        //Creating user's events:
        //For Nowak:
        EventModel eventModel3 = new EventModel();
        EventModel eventModel4 = new EventModel();

        eventModel3.setName("Nowak's party.");
        eventModel3.setDescription("Nowak will be drunk.");
        eventModel3.setBeginningDate(DateAndTimeUtil.getTimestamp("2018-12-01", "08:00 PM"));
        eventModel3.setLatitude(51.61479752787997);
        eventModel3.setLongitude(19.04609382464223);

        eventModel4.setName("Nowak's birthday party.");
        eventModel4.setDescription("Nowak turn's 42.");
        eventModel4.setBeginningDate(DateAndTimeUtil.getTimestamp("2018-08-24", "03:00 AM"));
        eventModel4.setLatitude(51.11279752787997);
        eventModel4.setLongitude(19.94609382464223);

        Set <EventModel> user1EventsSet = new HashSet<EventModel>();
        user1EventsSet.add(eventModel3);
        user1EventsSet.add(eventModel4);
        testUser1Model.setEvents(user1EventsSet);
        userService.updateUser(testUser1Model);

        //For Bogusz:
        EventModel eventModel5 = new EventModel();
        EventModel eventModel6 = new EventModel();

        eventModel5.setName("Bogusz's party.");
        eventModel5.setDescription("Bogusz will have fun.");
        eventModel5.setBeginningDate(DateAndTimeUtil.getTimestamp("2018-08-30", "04:00 PM"));
        eventModel5.setLatitude(52.61479752787997);
        eventModel5.setLongitude(18.04609382464223);

        eventModel6.setName("Bogusz's vehicle purchase.");
        eventModel6.setDescription("Bogusz will buy a tank for Polish roads.");
        eventModel6.setBeginningDate(DateAndTimeUtil.getTimestamp("2018-11-11", "10:00 PM"));
        eventModel6.setLatitude(51.79971127975278);
        eventModel6.setLongitude(19.46422394609382);

        //Adding category to event:
        EventCategoryModel eventCategoryModel1 = eventCategoryService.findEventCategoryModelByName("Automotive");
        eventModel6.addEventCategoryModel(eventCategoryModel1);

        Set <EventModel> user2EventsSet = new HashSet<EventModel>();
        user2EventsSet.add(eventModel5);
        user2EventsSet.add(eventModel6);
        testUser2Model.setEvents(user2EventsSet);

        testUser2Model.getUserDetailsModel().setFirstname("Janusz");
        testUser2Model.getUserDetailsModel().setLastname("Bogusz");
        testUser2Model.getUserDetailsModel().setBirthdate(DateAndTimeUtil.getTimestamp(1981,2,3,12,30));

        TimeLinePostModel timeLinePostModel1 = new TimeLinePostModel();
        TimeLinePostModel timeLinePostModel2 = new TimeLinePostModel();
        timeLinePostModel1.setText("First post, hello everyone!");
        timeLinePostModel2.setText("Second post!");

        Set <TimeLinePostModel> timeLinePostModelSet = new HashSet<TimeLinePostModel>();
        timeLinePostModel1.setUser(testUser2Model);
        timeLinePostModel2.setUser(testUser2Model);
        timeLinePostModel1.setPublishedDate(new Timestamp(System.currentTimeMillis()));
        timeLinePostModel2.setPublishedDate(new Timestamp(System.currentTimeMillis()));
        timeLinePostModelSet.add(timeLinePostModel1);
        timeLinePostModelSet.add(timeLinePostModel2);


        testUser2Model.setTimeLinePostModels(timeLinePostModelSet);

        userService.updateUser(testUser2Model);

        //We need to reload our UserModel entity from database before udpating it again, otherwise exception will occur.
//        testUser2Model = userService.findUserByEmail(testUser2Model.getEmail());

        //Deleting user's time line posts(ALL):
//        testUser2Model.setTimeLinePostModels(new HashSet<TimeLinePostModel>());
//        timeLinePostService.deleteAllTimeLinePostsByUserModel(testUser2Model);
//        userService.updateUser(testUser2Model);

        //Deleting user and all data related to him from database.
//        userService.deleteUserModelByEmail("Bogusz@wp.pl");

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        productionFill(); //use for production
//        testingFill(); //use for developing

    }

}
