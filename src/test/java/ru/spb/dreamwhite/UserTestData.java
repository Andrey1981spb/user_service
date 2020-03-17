package ru.spb.dreamwhite;

import ru.spb.dreamwhite.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.spb.dreamwhite.TrackerTestData.TRACKER1;

public class UserTestData {

    public static final int USER_ID = 1;

    public static final User USER = new User(1, "Alex", "email@gmail.com", "+78122524578", "Россия", "Санкт-Петербург","RU", true, TRACKER1);
    public static final User USER2 = new User(2, "Alex2", "gmail@gmail.com ", "+78122347391", "Россия", "Санкт-Петербург","RU", true, TRACKER1);
    public static final User USER3 = new User(3, "Alex3", "gmail@gmail.com", "+78122347398", "Россия", "Санкт-Петербург","RU", true, TRACKER1);

    public static User getNew() {
        return new User(null, "Gleb", "email@gmail.com", "+78122524585", "Россия", "Санкт-Петербург","RU", true, TRACKER1);
    }

    public static User getNewWithNotFormattedPhone() {
        return new User(null, "Gleb", "email@gmail.com", "88122524585", "Россия", "Санкт-Петербург","RU", true, TRACKER1);
    }

    public static User getNewWithNotValidPhone() {
        return new User(null, "Gleb", "email@gmail.com", "87451114585", "Россия", "Санкт-Петербург","RU", true, TRACKER1);
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

    public static ru.spb.dreamwhite.TestMatchers<User> USER_MATCHERS = ru.spb.dreamwhite.TestMatchers.useFieldsComparator(User.class, "email_valid");

}
