package ru.spb.dreamwhite.repository.user;

import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.User;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    /*
    List<User> getAll();
     */

    List<User> getByParameterOrAll(Map<String, String> paramsMap);

}
