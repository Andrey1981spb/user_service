package ru.spb.dreamwhite.model;

import ru.spb.dreamwhite.util.phoneUtil.ContactNumberConstraint;
import ru.spb.dreamwhite.util.phoneUtil.ContactNumberFormate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

@ContactNumberConstraint.List ( {
        @ContactNumberConstraint (
                number = "phone",
                locale = "locale",
                message = "phone not valid!"
        )
} )
@Entity
@Table ( name = "users" )
public class User {

    @Id
    @SequenceGenerator ( name = "userseq", sequenceName = "userseq", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "userseq" )
    private Integer id;

    @Column ( name = "name", nullable = false )
    @Size ( max = 100 )
    private String name;

    @Column ( name = "email", nullable = false )
    @Email
    @Size ( max = 25 )
    private String email;

    @ContactNumberFormate
    @Column ( name = "phone", nullable = false, unique = true )
    @Size ( max = 50 )
    private String phone;

    @Column ( name = "locale" )
    @Size ( max = 50 )
    private String locale;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String name, String phone, String email, String locale) {
        this(null, name, phone, email, locale);
    }

    public User(Integer id, String name, String phone, String email, String locale) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.locale = locale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", locale='" + locale + '\'' +
                '}';
    }
}
