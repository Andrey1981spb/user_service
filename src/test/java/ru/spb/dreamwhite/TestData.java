package ru.spb.dreamwhite;

import ru.spb.dreamwhite.model.User;

import java.util.Collections;
import java.util.Date;

public class TestData {

    public static final int USER_ID = 1;

    public static final User USER = new User(1, "Alex", "8-812-2563785", "gmail@gmail.com", "Россия");

    public static User getNew() {
        return new User(null, "Gleb", "8-812-1235656", "new@gmail.com", "Россия");
    }

    public static ru.spb.dreamwhite.TestMatchers<User> USER_MATCHERS = ru.spb.dreamwhite.TestMatchers.useFieldsComparator(User.class, "registered", "meals", "password");

}
