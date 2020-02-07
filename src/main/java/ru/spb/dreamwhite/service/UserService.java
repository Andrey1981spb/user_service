package ru.spb.dreamwhite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.InMemoryRepository;
import ru.spb.dreamwhite.repository.UserRepository;

import java.util.List;

import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFound;
import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    public UserRepository repository = new InMemoryRepository();

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }
}
