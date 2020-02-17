package ru.spb.dreamwhite.repository.country;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spb.dreamwhite.model.Country;

public interface CrudCountryRepository extends JpaRepository<Country, Integer> {

}
