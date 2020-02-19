package ru.spb.dreamwhite.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.service.UserService;

import java.util.List;

@Controller
public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        service.update(user);
    }

    public List<User> getByPhone(String phone) {
        log.info("getByPhone {}", phone);
        return service.getByPhone(phone);
    }

    public List<User> getByEmail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

}
