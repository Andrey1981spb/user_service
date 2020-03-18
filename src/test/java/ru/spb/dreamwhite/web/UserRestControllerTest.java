package ru.spb.dreamwhite.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.spb.dreamwhite.UserTestData;
import ru.spb.dreamwhite.TestUtil;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.service.UserService;
import ru.spb.dreamwhite.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.spb.dreamwhite.UserTestData.*;
import static ru.spb.dreamwhite.web.UserRestController.USER_URL;

public class UserRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService userService;

    UserRestControllerTest() {
        super(USER_URL);
    }

    @Test
    void getByIdWithoutTrackers() throws Exception {
        perform(doGet(USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHERS.contentJson(USER));
    }

    @Test
    void createWithLocation() throws Exception {
        User createdUser = UserTestData.getNew();
        ResultActions action = perform(doPost().jsonBody(createdUser))
                .andExpect(status().isCreated());

        User created = TestUtil.readFromJson(action, User.class);
        Integer newId = created.getId();
        createdUser.setId(newId);
        USER_MATCHERS.assertMatch(created, createdUser);
        USER_MATCHERS.assertMatch(userService.getByIdWithoutTrackers(newId), createdUser);
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(USER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> userService.getByIdWithoutTrackers(USER_ID));
    }

    @Test
    void getAll() throws Exception {
        perform(doGet())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHERS.contentJson(USER2, USER));
    }

    @Test
    void update() throws Exception {
        User updated = UserTestData.getUpdated();
        perform(doPut(USER_ID).jsonBody(updated))
                .andExpect(status().isNoContent());

        USER_MATCHERS.assertMatch(userService.getByIdWithoutTrackers(USER_ID), updated);
    }

    @Test
    void getByParametersOrAll() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_URL + "?phone=+78122347391"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHERS.contentJson(USER3));
    }

}
