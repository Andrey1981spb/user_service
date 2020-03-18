package ru.spb.dreamwhite.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spb.dreamwhite.model.Tracker;

import java.util.LinkedHashMap;
import java.util.Map;

import static ru.spb.dreamwhite.TrackerTestData.*;
import static ru.spb.dreamwhite.UserTestData.USER_ID;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-event.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@RunWith(SpringRunner.class)
public class TrackerServiceTest {

    @Autowired
    private TrackerService trackerService;

    private static Map<String, String> paramsMap = new LinkedHashMap<>();

    @Test
    public void create() throws Exception {
        Tracker newTracker = getNew();
        Tracker created = trackerService.create(new Tracker(newTracker));

        Integer newTrackerId = created.getId();
        newTracker.setId(newTrackerId);
        Integer newUserId = created.getUser_id();
        newTracker.setUser_id(newUserId);

        TRACKER_MATCHERS.assertMatch(created, newTracker);
        TRACKER_MATCHERS.assertMatch(trackerService.get(newTrackerId), newTracker);
    }

    @Test
    public void getByUser_Id() throws Exception {
        String userId = String.valueOf(USER_ID);
        paramsMap.put("user_id", userId);
        Tracker tracker = trackerService.getTrackerParameterOrAll(paramsMap).get(0);
        TRACKER_MATCHERS.assertMatch(tracker, TRACKER1);
    }

    @Test
    public void getByPhone() throws Exception {
        paramsMap.put("phone", "+78122524578");
        Tracker tracker = trackerService.getTrackerParameterOrAll(paramsMap).get(0);
        TRACKER_MATCHERS.assertMatch(tracker, TRACKER1);
    }

    @Test
    public void getByNameOfTracker() throws Exception {
        paramsMap.put("name", "Yandex");
        Tracker tracker = trackerService.getTrackerParameterOrAll(paramsMap).get(0);
        TRACKER_MATCHERS.assertMatch(tracker, TRACKER1);
    }

    @Test
    public void getAll() throws Exception {
        Tracker tracker = trackerService.getTrackerParameterOrAll(paramsMap).get(0);
        TRACKER_MATCHERS.assertMatch(tracker, TRACKER1);
    }

}
