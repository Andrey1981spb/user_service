package ru.spb.dreamwhite.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @SequenceGenerator(name = "token_seq", sequenceName = "token_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq")
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Column (name = "token")
    String token;

    @Column(name="created_date")
    private LocalDateTime createdDateTime;

    @Column(name="expiry_date")
    private LocalDateTime expiryDateTime;

    public Token() {
    }

    public Token(String token, LocalDateTime createdDateTime, LocalDateTime expiryDateTime, User user) {
        this.token=token;
        this.createdDateTime=createdDateTime;
        this.expiryDateTime=expiryDateTime;
        this.user=user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", user=" + user +
                ", token='" + token + '\'' +
                ", createdDate=" + createdDateTime +
                ", expiryDate=" + expiryDateTime +
                '}';
    }
}
