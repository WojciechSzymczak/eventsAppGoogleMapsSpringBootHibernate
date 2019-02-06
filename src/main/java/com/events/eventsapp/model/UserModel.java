package com.events.eventsapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class UserModel implements Serializable {

    public final static String USER_ROLE_ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false, unique = true)
    private Long id;

    @NotEmpty
    @Column(name="user_name", nullable=false, unique = true)
    private String name;

    @NotEmpty
    @Column(name="user_email", nullable=false, unique = true)
    private String email;

    @NotEmpty
    @Column(name="user_pass", nullable=false)
    private String password;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "event_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<EventModel> events;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_details_id")
    private UserDetailsModel userDetailsModel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<TimeLinePostModel> timeLinePostModels;

    //alphaRelationshipModels - these are the user's preferences towards other users.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alphaUserModel")
    private Set<RelationshipModel> alphaRelationshipModels;

    //betaRelationshipModels - these are the other's preferences towards the user.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "betaUserModel")
    private Set<RelationshipModel> betaRelationshipModels;

    //sendedMessages - the messages send by user to other users.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sender")
    private Set<MessageModel> sendedMessages;

    //receivedMessages - the messages received by user from other users.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recipient")
    private Set<MessageModel> receivedMessages;

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public int getActive() { return active; }

    public void setActive(int active) { this.active = active; }

    public Set<RoleModel> getRoles() { return roles; }

    public void setRoles(Set<RoleModel> roles) { this.roles = roles; }

    public Set<EventModel> getEvents() { return events; }

    public void setEvents(Set<EventModel> events) { this.events = events; }

    public void deleteEventById(Long eventId) {

            Set <EventModel> eventModelSet = this.getEvents();
            Iterator<EventModel> iterator = eventModelSet.iterator();

            while(iterator.hasNext()) {

                EventModel eventModel = iterator.next();

                if (eventModel.getId() == eventId) {
                    eventModelSet.remove(eventModel);
                    break;
                }

            }

    }

    public void addEvent(EventModel event) { this.events.add(event); }

    public UserDetailsModel getUserDetailsModel() { return userDetailsModel; }

    public void setUserDetailsModel(UserDetailsModel userDetailsModel) { this.userDetailsModel = userDetailsModel; }

    public Set<TimeLinePostModel> getTimeLinePostModels() { return timeLinePostModels; }

    public void setTimeLinePostModels(Set<TimeLinePostModel> timeLinePostModels) { this.timeLinePostModels = timeLinePostModels; }

    public Set<RelationshipModel> getAlphaRelationshipModels() {
        return alphaRelationshipModels;
    }

    public void setAlphaRelationshipModels(Set<RelationshipModel> alphaRelationshipModels) {
        this.alphaRelationshipModels = alphaRelationshipModels;
    }

    public Set<RelationshipModel> getBetaRelationshipModels() {
        return betaRelationshipModels;
    }

    public void setBetaRelationshipModels(Set<RelationshipModel> betaRelationshipModels) {
        this.betaRelationshipModels = betaRelationshipModels;
    }

    public boolean isInRole(String role) {
        for (RoleModel r : roles) {
            if (r.getRole().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public Set<MessageModel> getSendedMessages() {
        return sendedMessages;
    }

    public void setSendedMessages(Set<MessageModel> sendedMessages) {
        this.sendedMessages = sendedMessages;
    }

    public Set<MessageModel> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Set<MessageModel> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
}