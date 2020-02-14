package ru.spb.dreamwhite.service.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spb.dreamwhite.model.Country;
import ru.spb.dreamwhite.repository.country.CountryMapStore;
import ru.spb.dreamwhite.repository.country.CountryRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFound;
import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CountryService {

    private static final Map<String, String> countryMap = new LinkedHashMap<>();

    @Autowired
    public CountryRepository repository;

    public void getAll() {
        List<Country> countryList = repository.getAll();
        for (Country country : countryList) {
            countryMap.put(country.getTitle(), country.getCode());
            //      System.out.println(country.getTitle() + country.getCode());
        }

        CountryMapStore.setCountryShortcodes(countryMap);
    }


}
