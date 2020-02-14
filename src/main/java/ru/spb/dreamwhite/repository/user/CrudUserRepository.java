package ru.spb.dreamwhite.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.dreamwhite.model.User;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
}
