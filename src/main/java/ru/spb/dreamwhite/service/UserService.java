package ru.spb.dreamwhite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.country.CountryMapStore;
import ru.spb.dreamwhite.repository.user.UserRepository;
import ru.spb.dreamwhite.util.Formatter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFound;
import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(@Qualifier("anketUserRepository") UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        String shortCode = this.shortCodeCreate(user.getLocale());
        user.setPhone(Formatter.formate(user.getPhone(), shortCode));
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    /*
    public List<User> getAll() {
        return repository.getAll();
    }
     */

    public List<User> getByParameterOrAll(Map<String, String> paramsMap) {
        return repository.getByParameterOrAll(paramsMap);
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }

    private String shortCodeCreate(String locale) {
        String short_code;
        if ((locale != null) & (locale != "")) {
            short_code = CountryMapStore.getShortCode(locale);
        } else {
            short_code = null;
        }
        return short_code;
    }
}


