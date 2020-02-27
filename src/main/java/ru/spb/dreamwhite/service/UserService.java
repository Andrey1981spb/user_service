package ru.spb.dreamwhite.service;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.spb.dreamwhite.model.Token;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.token.TokenRepository;
import ru.spb.dreamwhite.repository.user.UserRepository;
import ru.spb.dreamwhite.util.emailUtil.CustomEventPublisher;
import ru.spb.dreamwhite.util.emailUtil.MailSend;
import ru.spb.dreamwhite.util.phoneUtil.Formatter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFoundWithId;
import static ru.spb.dreamwhite.util.phoneUtil.CountryHandler.countryHandle;

@Service("userService")
public class UserService {

    private UserRepository repository;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private static final int EXPIRATION = 2;

    @Autowired
    CustomEventPublisher customEventPublisher;

    @Autowired
    MailSend mailSend;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    public UserService(@Qualifier("anketUserRepository") UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) throws NumberParseException {
        Assert.notNull(user, "user must not be null");
        User userPassedToRepository = repository.save(provideWithFormattedPhone(user));

        long start = System.currentTimeMillis();
        //   customEventPublisher.doStuffAndPublishAnEvent(userPassedToRepository);
        mailSend.sendMail(user, this.createToken(userPassedToRepository));

        long finish = System.currentTimeMillis();
        LOGGER.info("PUBLISH DURATION IS " + String.valueOf(finish - start));

        userPassedToRepository.setEmail_valid(true);
        this.update(userPassedToRepository);
        return userPassedToRepository;
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<User> getByParameterOrAll(Map<String, String> paramsMap) throws NumberParseException {
        return repository.getByParameterOrAll(paramsMap);
    }

    public void update(User user) throws NumberParseException {
        Assert.notNull(user, "user must not be null");
        repository.save(provideWithFormattedPhone(user));
    }

    private Token createToken (User user){
        String token = UUID.randomUUID().toString();
        return tokenRepository.save(token, LocalDateTime.now().plusMinutes(EXPIRATION), user);
    }

    public Token getToken (String token){
        return tokenRepository.get(token);
    }

    private static User provideWithFormattedPhone(User user) throws NumberParseException {
        String short_code = countryHandle(user.getLocale());
        user.setShort_code(short_code);
        user.setPhone(Formatter.formate(user.getPhone(), short_code));
        return user;
    }

}


