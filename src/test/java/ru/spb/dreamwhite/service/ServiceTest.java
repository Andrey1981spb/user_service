package ru.spb.dreamwhite.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spb.dreamwhite.model.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ru.spb.dreamwhite.TestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-event.xml"
})
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void create() throws Exception {
        User newUser = getNew();
        User created = userService.create(new User(newUser));
        Integer newId = created.getId();
        newUser.setId(newId);
        assertMatch((Iterable<User>) created, newUser);
        assertMatch((Iterable<User>) userService.get(newId), newUser);
    }

    @Test
    public void getByParametersOrAll() throws Exception {
        Map<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("email", null);
        paramsMap.put("phone", "+78122524578");

        List<User> userList= userService.getByParameterOrAll(paramsMap);
        assertMatch(userList, USER);
    }

    @Test
    public void getByParametersNotFound() throws Exception {
        Map<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("email", null);
        paramsMap.put("phone", "+78122524579");

        List<User> userList= userService.getByParameterOrAll(paramsMap);
        assertMatch(userList, USER);
    }

}
