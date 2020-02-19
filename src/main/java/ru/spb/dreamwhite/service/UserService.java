package ru.spb.dreamwhite.service;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.user.UserRepository;
import ru.spb.dreamwhite.util.Formatter;

import java.util.List;

import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFound;
import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService (@Qualifier ( "anketUserRepository" ) UserRepository repository){
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        user.setPhone(Formatter.formate(user.getPhone()));
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByPhone(String phone) {
        Assert.notNull(phone, "phone must not be null");
        return checkNotFound(repository.getByPhone(phone), "phone=" + phone);
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
