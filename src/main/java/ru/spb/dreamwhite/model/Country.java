package ru.spb.dreamwhite.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table (name = "codes")
public class Country {

    @Id
    @SequenceGenerator ( name = "global_seq", sequenceName = "global_seq", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "global_seq" )
    private Integer id;

    @Column (name = "title", nullable = false, unique = true)
    @Size (max = 100)
    private String title;

    @Column(name = "code", nullable = false)
    @Size(min = 1, max = 3)
    private String code;

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
