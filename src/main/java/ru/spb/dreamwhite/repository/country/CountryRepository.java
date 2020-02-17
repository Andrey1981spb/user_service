package ru.spb.dreamwhite.repository.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.Country;

import java.util.List;

@Repository
public class CountryRepository {

    @Autowired
    private CrudCountryRepository crudCountryRepository;

    public List<Country> getAll() {
        return crudCountryRepository.findAll();
    }

}
