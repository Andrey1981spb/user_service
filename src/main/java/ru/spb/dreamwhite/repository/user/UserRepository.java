package ru.spb.dreamwhite.repository.user;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.spb.dreamwhite.model.User;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository {

    User save(User user) throws MethodArgumentNotValidException;

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    List<User> getByParameterOrAll(Map<String, String> paramsMap) throws NumberParseException;

}
