package ru.spb.dreamwhite.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "trackers")
@Component
public class Tracker {

    @Id
    @SequenceGenerator(name = "tracker_seq", sequenceName = "tracker_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tracker_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @Size(max = 40)
    private String name;

    @Column(name = "value", nullable = false)
    @Size(max = 150)
    private String value;

    @Column(name = "user_id")
    private Integer user_id;

    @Transient
    private String phone;

    @Transient
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Tracker() {
    }

    public Tracker(Tracker tracker) {
        this(tracker.getId(), tracker.getName(), tracker.getValue(), tracker.getUser_id(), tracker.getPhone(), tracker.getEmail(), tracker.getUser());
    }

    public Tracker(Integer id, String name, String value, Integer user_id, String phone, String email, User user) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.user_id = user_id;
        this.phone = phone;
        this.email = email;
        this.user = user;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
