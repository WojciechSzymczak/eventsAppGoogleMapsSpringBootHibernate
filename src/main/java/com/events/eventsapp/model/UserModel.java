package com.events.eventsapp.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class UserModel implements Serializable {

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

    @Column(name="user_join_date")
    private String joindate;

    @Column(name="user_birth_date")
    private String birthdate;

    @Column(name="user_first_name")
    private String firstname;

    @Column(name="user_last_name")
    private String lastname;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> roles;


    public Long getId() {
        return id;
    }

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

    public String getJoindate() { return joindate; }

    public void setJoindate(String joindate) { this.joindate = joindate; }

    public String getBirthdate() { return birthdate; }

    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public int getActive() { return active; }

    public void setActive(int active) { this.active = active; }

    public Set<RoleModel> getRoles() { return roles; }

    public void setRoles(Set<RoleModel> roles) { this.roles = roles; }

}