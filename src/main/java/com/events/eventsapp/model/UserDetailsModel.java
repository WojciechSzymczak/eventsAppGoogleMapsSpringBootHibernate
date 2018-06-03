package com.events.eventsapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_details")
public class UserDetailsModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique = true)
    private Long id;

    @Column(name="user_join_date")
    private String joindate;

    @Column(name="user_birth_date")
    private String birthdate;

    @Column(name="user_first_name")
    private String firstname;

    @Column(name="user_last_name")
    private String lastname;

    @Column(name = "user_id")
    private UserModel userModel;

    @OneToOne(mappedBy = "user_details_id")
    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getJoindate() { return joindate; }

    public void setJoindate(String joindate) { this.joindate = joindate; }

    public String getBirthdate() { return birthdate; }

    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

}