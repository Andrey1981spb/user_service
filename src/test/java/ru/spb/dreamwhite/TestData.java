package ru.spb.dreamwhite;

import ru.spb.dreamwhite.model.User;

public class TestData {

    public static final int USER_ID = 2;

    public static final User USER = new User(2, "Nick", 89997777, "gmail@gmail.com");

    public static TestMatchers<User> USER_MATCHERS = TestMatchers.useFieldsComparator(User.class, "registered", "meals", "password");

}
