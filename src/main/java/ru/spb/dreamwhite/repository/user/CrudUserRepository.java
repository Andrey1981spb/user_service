package ru.spb.dreamwhite.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.User;

@Repository
public interface CrudUserRepository extends JpaRepository<User, Integer> {
}
