package ru.spb.dreamwhite.repository.user;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.service.UserService;
import ru.spb.dreamwhite.util.Formatter;
import ru.spb.dreamwhite.util.phoneUtil.CountryHandler;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFound;

@Repository
public class AnketUserRepository implements UserRepository {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    @Autowired
    private CrudUserRepository crudRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getByParameterOrAll(Map<String, String> paramsMap) throws NumberParseException {
        String email = paramsMap.get("email");
        String locale = paramsMap.get("locale");
        String phone = Formatter.formate(paramsMap.get("phone"), CountryHandler.countryHandle(locale));

        List<User> users = em.createQuery("SELECT u FROM User u WHERE (:emailValue is null OR u.email=:emailValue) " +
                "AND (:phoneValue is null OR u.phone=:phoneValue)" +
                "AND (:localeValue is null OR u.locale=:localeValue)", User.class).
                setParameter("emailValue", email).
                setParameter("phoneValue", phone).
                setParameter("localeValue", locale).
                getResultList();

        return checkNotFound(users.size()==0? null :users, "not found");
    }

}
