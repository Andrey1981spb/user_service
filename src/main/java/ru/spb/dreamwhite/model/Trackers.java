package ru.spb.dreamwhite.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "trackers")
@Component
public class Trackers {

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

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "phone", nullable = false)
    @Size(max = 50)
    private String phone;

}
