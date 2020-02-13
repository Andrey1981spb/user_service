package ru.spb.dreamwhite.repository.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.Country;
import ru.spb.dreamwhite.repository.CrudUserRepository;

import java.util.List;


@Repository
public class CountryRepository {

    @Autowired
    private CrudUserRepository crudRepository;

    public List<Country> getAll() {
        return crudRepository.findAll();
    }


}
