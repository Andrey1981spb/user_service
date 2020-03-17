package ru.spb.dreamwhite;

import ru.spb.dreamwhite.model.Tracker;
import static ru.spb.dreamwhite.UserTestData.USER;
import static ru.spb.dreamwhite.UserTestData.USER_ID;

public class TrackerTestData {

    public static final Tracker TRACKER1 = new Tracker(1, "Yandex", "dfvdfv6d79dfvdfv09d0vd", USER_ID, "+78122524578", "email@gmail.com", USER);
    public static final Tracker TRACKER2 = new Tracker(2, "Google", "dfvdfvy980zcxvv0-dfv-d", USER_ID, "+78122524578", "email@gmail.com", USER);

    public static Tracker getNew() {
        return new Tracker(null, "Facebook", "789dbvdv97dvfdfvvdd444", null,"+78122524578", "email@gmail.com", null);
    }

    public static TestMatchers<Tracker> TRACKER_MATCHERS = TestMatchers.useFieldsComparator(Tracker.class, "user", "user_id" , "phone", "email");

}
