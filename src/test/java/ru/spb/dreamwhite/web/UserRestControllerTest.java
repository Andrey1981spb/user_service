package ru.spb.dreamwhite.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.spb.dreamwhite.TestData;
import ru.spb.dreamwhite.TestUtil;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.service.UserService;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.spb.dreamwhite.TestData.*;

public class UserRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService userService;

    UserRestControllerTest() {
        super(UserRestController.REST_URL);
    }

    @Test
    void get() throws Exception {
        perform(doGet(USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect((ResultMatcher) content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(USER_MATCHERS.contentJson(USER));
    }

    @Test
    void createWithLocation() throws Exception {
        User createdUser = TestData.getNew();
        ResultActions action = perform(doPost().jsonBody(createdUser))
                .andExpect(status().isCreated());

        User created = TestUtil.readFromJson(action, User.class);
        Integer newId = created.getId();
        createdUser.setId(newId);
        USER_MATCHERS.assertMatch(created, createdUser);
        USER_MATCHERS.assertMatch(userService.get(newId), createdUser);
    }

}
