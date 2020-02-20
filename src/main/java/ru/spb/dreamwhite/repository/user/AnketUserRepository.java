package ru.spb.dreamwhite.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

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
    public List<User> getByParameterOrAll(Map<String, String> paramsMap) {
        String email = paramsMap.get("email");
        String phone = paramsMap.get("phone");

        List<User> users = em.createQuery("SELECT u FROM User u WHERE (:emailValue is null OR u.email=:emailValue) AND (:phoneValue is null OR u.phone=:phoneValue)", User.class).
                setParameter("emailValue", email).
                setParameter("phoneValue", phone).
                getResultList();
        return users;
    }

}
