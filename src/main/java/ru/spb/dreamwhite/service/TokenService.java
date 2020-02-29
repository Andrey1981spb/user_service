package ru.spb.dreamwhite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spb.dreamwhite.model.Token;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.token.TokenRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("tokenService")
public class TokenService {

    private static final int EXPIRATION = 1;

    @Autowired
    TokenRepository tokenRepository;

    public Token createToken(User user) {
        String token = UUID.randomUUID().toString();
        return tokenRepository.save(token, LocalDateTime.now().plusMinutes(EXPIRATION), user);
    }

    public Token getToken(String token) {
        return tokenRepository.get(token);
    }

}


