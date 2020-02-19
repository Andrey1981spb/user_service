package ru.spb.dreamwhite.repository.user;

import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.User;

import java.util.List;

@Repository
public interface UserRepository {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    List<User> getByPhone(String phone);

    // null if not found
    List<User> getByEmail(String phone);

    List<User> getAll();

}
