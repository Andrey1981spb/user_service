package ru.spb.dreamwhite.repository.country;

import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.Country;

import java.util.List;

@Repository
public class CountryRepository {

    private CrudCountryRepository crudRepository;

    public List<Country> getAll() {
        return crudRepository.findAll();
    }

}
