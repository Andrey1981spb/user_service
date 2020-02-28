package ru.spb.dreamwhite.service;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.user.UserRepository;
import ru.spb.dreamwhite.util.emailUtil.CustomEventPublisher;
import ru.spb.dreamwhite.util.emailUtil.MailSend;
import ru.spb.dreamwhite.util.phoneUtil.Formatter;

import java.util.List;
import java.util.Map;

import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFound;
import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFoundWithId;
import static ru.spb.dreamwhite.util.phoneUtil.CountryHandler.countryHandle;

@Service("userService")
public class UserService {

    private UserRepository repository;

    @Autowired
    CustomEventPublisher customEventPublisher;

    @Autowired
    MailSend mailSend;

    @Autowired
    TokenService tokenService;

    @Autowired
    public UserService(@Qualifier("anketUserRepository") UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) throws NumberParseException {
        Assert.notNull(user, "user must not be null");
        User userPassedToRepository = repository.save(provideWithFormattedPhone(user));
        mailSend.sendMail(user, tokenService.createToken(userPassedToRepository));
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
        List<User> users = repository.getByParameterOrAll(paramsMap);
        return checkNotFound(users,"not found");
    }

    public void update(User user) throws NumberParseException {
        Assert.notNull(user, "user must not be null");
        repository.save(provideWithFormattedPhone(user));
    }

    private static User provideWithFormattedPhone(User user) throws NumberParseException {
        String short_code = countryHandle(user.getLocale());
        user.setShort_code(short_code);
        user.setPhone(Formatter.formate(user.getPhone(), short_code));
        return user;
    }

}


