package ru.spb.dreamwhite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.dreamwhite.model.Country;

//@Repository
@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<Country, Integer> {
}
