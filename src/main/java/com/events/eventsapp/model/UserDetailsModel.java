package com.events.eventsapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users_details")
public class UserDetailsModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique = true)
    private Long id;

    @Column(name="user_join_date")
    private Timestamp joindate;

    @Column(name="user_birth_date")
    private Timestamp birthdate;

    @Column(name="user_first_name")
    private String firstname;

    @Column(name="user_last_name")
    private String lastname;

    @OneToOne(mappedBy = "userDetailsModel")
    private UserModel userModel;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Timestamp getJoindate() { return joindate; }

    public void setJoindate(Timestamp joindate) { this.joindate = joindate; }

    public Timestamp getBirthdate() { return birthdate; }

    public void setBirthdate(Timestamp birthdate) { this.birthdate = birthdate; }

}