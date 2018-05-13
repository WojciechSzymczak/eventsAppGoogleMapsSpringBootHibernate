package com.events.eventsapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "events")
public class EventModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false, unique = true)
    private Long id;

    @NotEmpty
    @Column(name = "event_name", nullable = false, unique = true)
    private String name;

    @Column(name = "event_beginning_date")
    private Timestamp beginningDate;

    @Column(name = "event_description")
    private String description;

    @Column(name = "event_longitude")
    private Double longitude;

    @Column(name = "event_latitude")
    private Double latitude;

    @ManyToMany(mappedBy = "events")
    private Set<UserModel> users;

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

    public Timestamp getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Timestamp beginningDate) {
        this.beginningDate = beginningDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }

}