package ru.spb.dreamwhite.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.User;

import java.util.List;

@Repository
public class AnketUserRepository implements UserRepository {

    @Autowired
    private CrudUserRepository crudRepository;

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public User getByPhone(String phone) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
