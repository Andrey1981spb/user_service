package ru.spb.dreamwhite.repository;

import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryRepository implements UserRepository {

    private final Map<Integer, User> testMap = new HashMap<>();

    private AtomicInteger counter = new AtomicInteger(2);

    public InMemoryRepository () {
        User testUser = new User(2, "Nick", "89997777", "gmail@gmail.com", "Белорусь");
        testMap.put(2, testUser);
    }

    @Override
    public User save(User user) {
        Integer increment = counter.incrementAndGet();
        user.setId(increment);
        testMap.putIfAbsent(increment, user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return testMap.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
