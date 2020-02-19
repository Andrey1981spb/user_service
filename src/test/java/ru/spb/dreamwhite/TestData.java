package ru.spb.dreamwhite;

import ru.spb.dreamwhite.model.User;

public class TestData {

    public static final int USER_ID = 34;

    public static final User USER = new User(34, "Alex", "gmail@gmail.com", "8-812-2563456", "Россия");
    public static final User USER2 = new User(33, "Andrey", "email@gmail.com", "8-812-2563745", "Россия");

    public static User getNew() {
        return new User(null, "Gleb", "email@gmail.com","8-812-1234578",  "Россия");
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }

    public static ru.spb.dreamwhite.TestMatchers<User> USER_MATCHERS = ru.spb.dreamwhite.TestMatchers.useFieldsComparator(User.class, "phone");

}
