package ru.spb.dreamwhite.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.spb.dreamwhite.service.TrackerService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static ru.spb.dreamwhite.TrackerTestData.*;
import static ru.spb.dreamwhite.web.TrackerController.TrackerRestController.TRACKER_URL;

public class TrackerRestControllerTest extends AbstractControllerTest {

    @Autowired
    private TrackerService trackerService;

    TrackerRestControllerTest() {
        super(TRACKER_URL);
    }

    @Test
    void getByNameOfTracker() throws Exception {
        perform(MockMvcRequestBuilders.get(TRACKER_URL + "?name=Yandex"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TRACKER_MATCHERS.contentJson(TRACKER1, TRACKER2));
    }

}
