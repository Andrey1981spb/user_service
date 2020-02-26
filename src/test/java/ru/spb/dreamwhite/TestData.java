package ru.spb.dreamwhite;

import ru.spb.dreamwhite.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static final int USER_ID = 84;

    public static final User USER = new User(84, "Gleb", "email@gmail.com", "+78122524578", "Россия", "RU");
    public static final User USER2 = new User(60, "Alex2", "gmail@gmail.com ", "+78122347391", "Россия", "RU");
    public static final User USER3 = new User(58, "Alex2", "gmail@gmail.com", "88122347398", "Россия", "RU");


    public static User getNew() {
        return new User(null, "Gleb", "email@gmail.com", "8-812-2524585", "Россия", "RU");
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat (actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static ru.spb.dreamwhite.TestMatchers<User> USER_MATCHERS = ru.spb.dreamwhite.TestMatchers.useFieldsComparator(User.class, "phone");

}
