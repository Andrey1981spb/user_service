package ru.spb.dreamwhite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ru.spb.dreamwhite.model.Country;
import ru.spb.dreamwhite.repository.CrudUserRepository;

import java.util.List;


//@Repository
public class CountryRepository {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudUserRepository crudRepository;

    public List<Country> getAll() {
        return crudRepository.findAll();
    }


}
