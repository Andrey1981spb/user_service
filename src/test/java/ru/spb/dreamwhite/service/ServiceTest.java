package ru.spb.dreamwhite.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.user.UserRepository;
import ru.spb.dreamwhite.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.spb.dreamwhite.TestData.*;

@SpringJUnitConfig ( locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-event.xml"
} )
@Sql ( scripts = "classpath:db/populateDB.sql", config = @SqlConfig ( encoding = "UTF-8" ) )
@RunWith ( SpringRunner.class )
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() throws Exception {
        User newUser = getNew();
        User created = userService.create(new User(newUser));
        Integer newId = created.getId();
        newUser.setId(newId);
        USER_MATCHERS.assertMatch(created, newUser);
        USER_MATCHERS.assertMatch(userService.get(newId), newUser);
    }

    @Test
    public void createWithFormattedPhone() throws Exception {
        User newUser = getNewWithNotFormattedPhone();
        User created = userService.create(new User(newUser));
        Integer newId = created.getId();
        User userWithFormattedPhone = getNew();
        userWithFormattedPhone.setId(newId);
        USER_MATCHERS.assertMatch(userService.get(newId), userWithFormattedPhone);
    }

    @Test
    public void createWithNotValidPhone() throws Exception {
        User newUser = getNewWithNotValidPhone();
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(new User(newUser)));
    }

    @Test
    public void getByPhone() throws Exception {
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("email", null);
        paramsMap.put("phone", "+78122524578");

        User user = userService.getByParameterOrAll(paramsMap).get(0);
        USER_MATCHERS.assertMatch(user, USER);
    }

    @Test
    public void getByEmail() throws Exception {
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("email", "email@gmail.com");
        paramsMap.put("phone", null);

        User user = userService.getByParameterOrAll(paramsMap).get(0);
        USER_MATCHERS.assertMatch(user, USER);
    }

    @Test
    public void getByEmailAndPhone() throws Exception {
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("email", "gmail@gmail.com");
        paramsMap.put("phone", "88122347398");
        paramsMap.put("locale", "Россия");

        User user = userService.getByParameterOrAll(paramsMap).get(0);
        USER_MATCHERS.assertMatch(user, USER3);
    }

    @Test
    public void getByParametersNotFound() throws Exception {
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("email", null);
        paramsMap.put("phone", "+78122524579");
        assertThrows(NotFoundException.class, () ->
                userService.getByParameterOrAll(paramsMap).get(0));
    }

}
