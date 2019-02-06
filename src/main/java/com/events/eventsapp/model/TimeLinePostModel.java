package com.events.eventsapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "time_line_post")
public class TimeLinePostModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "published_date", nullable =  false)
    private Timestamp publishedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    /**
     * canDelete is information for the view, if there should be a ,,delete'' button shown near the user's post.
     * This is needed to allow administrators to delete other users posts.
     */
    @Transient
    private Boolean canDelete;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public Timestamp getPublishedDate() { return publishedDate; }

    public void setPublishedDate(Timestamp publishedDate) { this.publishedDate = publishedDate; }

    public UserModel getUser() { return user; }

    public void setUser(UserModel user) { this.user = user; }

    public Boolean getCanDelete() { return canDelete; }

    public void setCanDelete(Boolean canDelete) { this.canDelete = canDelete; }
}
