package ru.spb.dreamwhite.model;

import org.springframework.stereotype.Component;
import ru.spb.dreamwhite.util.phoneUtil.ContactNumberConstraint;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.util.*;

@ContactNumberConstraint.List({
        @ContactNumberConstraint(
                number = "phone",
                sh = "short_code",
                message = "phone not valid!"
        )
})
@Entity
@Table(name = "users")
@Component
public class User {

    @Id
    @SequenceGenerator(name = "userseq", sequenceName = "userseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userseq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @Size(max = 100)
    private String name;

    @Column(name = "email", nullable = false)
    @Email
    @Size(max = 100)
    private String email;

    //  @ContactNumberFormat
    @Column(name = "phone", nullable = false, unique = true)
    @Size(max = 50)
    private String phone;

    @Column(name = "locale")
    @Size(max = 50)
    private String locale;

    @Column(name = "city")
    private String city;

    @Column(name = "short_code")
    @Size(max = 4)
    private String short_code;

    @Column(name = "email_valid")
    private boolean email_valid;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Tracker> trakers;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPhone(), u.getLocale(), u.getCity(), u.getShort_code(), u.isEmail_valid(), u.getTrakers());
    }

    public User(Integer id, String name, String email, String phone, String locale, String city, String short_code, boolean email_valid, List<Tracker> trackers) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.locale = locale;
        this.city = city;
        this.short_code = short_code;
        this.email_valid = email_valid;
        this.trakers = trackers;
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

    public String getShort_code() {
        return short_code;
    }

    public void setShort_code(String short_code) {
        this.short_code = short_code;
    }

    public boolean isEmail_valid() {
        return email_valid;
    }

    public void setEmail_valid(boolean email_valid) {
        this.email_valid = email_valid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Tracker> getTrakers() {
        return trakers;
    }

    public void setTrakers(List<Tracker> trakers) {
        this.trakers = trakers;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", locale='" + locale + '\'' +
                ", city='" + city + '\'' +
                ", short_code='" + short_code + '\'' +
                ", email_valid='" + email_valid + '\'' +
                '}';
    }
}
