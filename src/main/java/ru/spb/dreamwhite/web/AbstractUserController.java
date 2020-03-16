package ru.spb.dreamwhite.web;

import com.google.i18n.phonenumbers.NumberParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.service.UserService;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected UserService service;

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) throws NumberParseException, MethodArgumentNotValidException {
        log.info("create {}", user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) throws NumberParseException, MethodArgumentNotValidException {
        log.info("update {} with id={}", user, id);
        user.setId(id);
        service.update(user);
    }

    public List<User> getUserByParameterOrAll(Map<String, String> paramsMap) throws UnsupportedEncodingException, NumberParseException {
        return service.getUserByParameterOrAll(paramsMap);
    }

}
