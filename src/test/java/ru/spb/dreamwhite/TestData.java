package ru.spb.dreamwhite;

import ru.spb.dreamwhite.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static final int USER_ID = 34;

    public static final User USER = new User(59, "Alex2", "tmail@gmail.com", "+78122347390", "Россия");
    public static final User USER2 = new User(60, "Alex2", "gmail@gmail.com ", "+78122347391", "Россия");
    public static final User USER3 = new User(58, "Alex2", "gmail@gmail.com", "88122347398", "Россия");


    public static User getNew() {
        return new User(null, "Gleb", "email@gmail.com", "8-812-1234578", "Россия");
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
        assertThat (actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static ru.spb.dreamwhite.TestMatchers<User> USER_MATCHERS = ru.spb.dreamwhite.TestMatchers.useFieldsComparator(User.class, "phone");


}
