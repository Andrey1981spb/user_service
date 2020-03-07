package ru.spb.dreamwhite.repository.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.dreamwhite.model.Token;
import ru.spb.dreamwhite.model.User;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Repository
public class TokenRepository {

    @Autowired
    private CrudTokenRepository crudTokenRepository;

    @Transactional
    public Token save(String token, LocalDateTime expiryDateTime, User user) {
        Token createdToken = new Token(token, now(), expiryDateTime, user);
        return crudTokenRepository.save(createdToken);
    }

    public Token get(String token) {
        return crudTokenRepository.getByToken(token);
    }

    public boolean delete(String token) {
        return crudTokenRepository.delete(token) != 0;
    }

}
