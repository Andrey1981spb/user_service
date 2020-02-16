package ru.spb.dreamwhite.repository.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.Country;

@Repository
public interface CrudCountryRepository extends JpaRepository<Country, Integer> {

}
